package com.migu.schedule;


import com.migu.schedule.constants.ReturnCodeKeys;
import com.migu.schedule.info.TaskInfo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/*
 *类名和方法不能修改
 */
public class Schedule {

    /**
     * nodeId
     */
    private Set<Integer> nodes = new HashSet<Integer>();

    /**
     * taskId
     */
    private Set<Integer> tasks = new HashSet<Integer>();

    /**
     * Task集合
     */
    private Map<Integer, Integer> consumptions = new HashMap<Integer, Integer>();

    /**
     * Task状态
     */
    private Map<Integer, List<TaskInfo>> taskStatus = new HashMap<Integer, List<TaskInfo>>();

    /**
     * threshold
     */
    private int threshold = 0;

    public int init() {
        nodes.clear();
        tasks.clear();
        consumptions.clear();
        taskStatus.clear();
        return ReturnCodeKeys.E001;
    }


    public int registerNode(int nodeId) {
        if (nodeId <= 0) {
            return ReturnCodeKeys.E004;
        }

        if (nodes.contains(nodeId)) {
            return ReturnCodeKeys.E005;
        }

        nodes.add(nodeId);
        return ReturnCodeKeys.E003;
    }


    public int unregisterNode(int nodeId) {
        if (!nodes.contains(nodeId)) {
            return ReturnCodeKeys.E007;
        }

        nodes.remove(new Integer(nodeId));
        return ReturnCodeKeys.E006;
    }


    public int addTask(int taskId, int consumption) {
        if (taskId <= 0) {
            return ReturnCodeKeys.E009;
        }

        if (tasks.contains(taskId)) {
            return ReturnCodeKeys.E010;
        }

        tasks.add(taskId);
        consumptions.put(taskId, consumption);
        return ReturnCodeKeys.E008;
    }


    public int deleteTask(int taskId) {
        if (!tasks.contains(taskId)) {
            return ReturnCodeKeys.E012;
        }

        tasks.remove(new Integer(taskId));
        consumptions.remove(new Integer(taskId));
        return ReturnCodeKeys.E011;
    }


    public int scheduleTask(int threshold) {
        if (tasks.isEmpty()) {
            return ReturnCodeKeys.E014;
        }

        if (threshold > 0 && !nodes.isEmpty()) {
            if (tasks.isEmpty()) {
                return ReturnCodeKeys.E013;
            }
        }

        return ReturnCodeKeys.E013;
    }


    public int queryTaskStatus(List<TaskInfo> tasks) {
        for (Integer nodeId : taskStatus.keySet()) {
            tasks.addAll(taskStatus.get(nodeId));
        }
        return ReturnCodeKeys.E015;
    }
}
