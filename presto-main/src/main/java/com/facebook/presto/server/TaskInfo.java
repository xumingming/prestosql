/*
 * Copyright 2004-present Facebook. All Rights Reserved.
 */
package com.facebook.presto.server;

import com.facebook.presto.server.PageBuffer.BufferState;
import com.facebook.presto.tuple.TupleInfo;
import com.google.common.base.Function;
import com.google.common.base.Objects;
import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableMap;
import org.codehaus.jackson.annotate.JsonCreator;
import org.codehaus.jackson.annotate.JsonProperty;

import javax.annotation.concurrent.Immutable;
import java.net.URI;
import java.util.List;
import java.util.Map;

@Immutable
public class TaskInfo
{
    private final String taskId;
    private final URI self;
    private final Map<String, BufferState> outputBufferStates;
    private final List<TupleInfo> tupleInfos;
    private final TaskState state;
    private final int bufferedPages;
    private final int splits;
    private final int startedSplits;
    private final int completedSplits;
    private final long splitCpuTime;
    private final long inputDataSize;
    private final long inputPositionCount;
    private final long completedDataSize;
    private final long completedPositionCount;
    private final long outputDataSize;
    private final long outputPositionCount;

    @JsonCreator
    public TaskInfo(@JsonProperty("taskId") String taskId,
            @JsonProperty("self") URI self,
            @JsonProperty("outputBufferStates") Map<String, BufferState> outputBufferStates,
            @JsonProperty("tupleInfos") List<TupleInfo> tupleInfos,
            @JsonProperty("state") TaskState state,
            @JsonProperty("bufferedPages") int bufferedPages,
            @JsonProperty("splits") int splits,
            @JsonProperty("startedSplits") int startedSplits,
            @JsonProperty("completedSplits") int completedSplits,
            @JsonProperty("splitCpuTime") long splitCpuTime,
            @JsonProperty("inputDataSize") long inputDataSize,
            @JsonProperty("inputPositionCount") long inputPositionCount,
            @JsonProperty("completedDataSize") long completedDataSize,
            @JsonProperty("completedPositionCount") long completedPositionCount,
            @JsonProperty("outputDataSize") long outputDataSize,
            @JsonProperty("outputPositionCount") long outputPositionCount)
    {
        Preconditions.checkNotNull(taskId, "taskId is null");
        Preconditions.checkNotNull(self, "self is null");
        Preconditions.checkNotNull(outputBufferStates, "outputIds is null");
        Preconditions.checkNotNull(tupleInfos, "tupleInfos is null");
        this.taskId = taskId;
        this.self = self;
        this.outputBufferStates = ImmutableMap.copyOf(outputBufferStates);
        this.tupleInfos = tupleInfos;
        this.state = state;
        this.bufferedPages = bufferedPages;
        this.splits = splits;
        this.startedSplits = startedSplits;
        this.completedSplits = completedSplits;
        this.splitCpuTime = splitCpuTime;
        this.inputDataSize = inputDataSize;
        this.inputPositionCount = inputPositionCount;
        this.completedDataSize = completedDataSize;
        this.completedPositionCount = completedPositionCount;
        this.outputDataSize = outputDataSize;
        this.outputPositionCount = outputPositionCount;
    }

    @JsonProperty
    public String getTaskId()
    {
        return taskId;
    }

    @JsonProperty
    public URI getSelf()
    {
        return self;
    }

    @JsonProperty
    public Map<String, BufferState> getOutputBufferStates()
    {
        return outputBufferStates;
    }

    @JsonProperty
    public List<TupleInfo> getTupleInfos()
    {
        return tupleInfos;
    }

    @JsonProperty
    public TaskState getState()
    {
        return state;
    }

    @JsonProperty
    public int getBufferedPages()
    {
        return bufferedPages;
    }

    @JsonProperty
    public int getSplits()
    {
        return splits;
    }

    @JsonProperty
    public int getStartedSplits()
    {
        return startedSplits;
    }

    @JsonProperty
    public int getCompletedSplits()
    {
        return completedSplits;
    }

    @JsonProperty
    public long getSplitCpuTime()
    {
        return splitCpuTime;
    }

    @JsonProperty
    public long getInputDataSize()
    {
        return inputDataSize;
    }

    @JsonProperty
    public long getInputPositionCount()
    {
        return inputPositionCount;
    }

    @JsonProperty
    public long getCompletedDataSize()
    {
        return completedDataSize;
    }

    @JsonProperty
    public long getCompletedPositionCount()
    {
        return completedPositionCount;
    }

    @JsonProperty
    public long getOutputDataSize()
    {
        return outputDataSize;
    }

    @JsonProperty
    public long getOutputPositionCount()
    {
        return outputPositionCount;
    }

    @Override
    public String toString()
    {
        return Objects.toStringHelper(this)
                .add("taskId", taskId)
                .add("state", state)
                .toString();
    }


    public static Function<TaskInfo, TaskState> taskStateGetter()
    {
        return new Function<TaskInfo, TaskState>()
        {
            @Override
            public TaskState apply(TaskInfo taskInfo)
            {
                return taskInfo.getState();
            }
        };
    }
}
