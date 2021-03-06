package com.example.elasticagent.requests;

import com.example.elasticagent.AgentInstances;
import com.example.elasticagent.PluginRequest;
import com.example.elasticagent.executors.AgentStatusReportExecutor;
import com.example.elasticagent.models.JobIdentifier;
import com.example.elasticagent.views.ViewBuilder;
import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.annotations.Expose;

import java.util.Objects;

public class AgentStatusReportRequest {
    private static final Gson GSON = new GsonBuilder().excludeFieldsWithoutExposeAnnotation()
            .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
            .create();

    @Expose
    private String elasticAgentId;

    @Expose
    private JobIdentifier jobIdentifier;

    public AgentStatusReportRequest() {
    }

    public AgentStatusReportRequest(String elasticAgentId, JobIdentifier jobIdentifier) {
        this.elasticAgentId = elasticAgentId;
        this.jobIdentifier = jobIdentifier;
    }

    public static AgentStatusReportRequest fromJSON(String json) {
        return GSON.fromJson(json, AgentStatusReportRequest.class);
    }

    public String getElasticAgentId() {
        return elasticAgentId;
    }

    public JobIdentifier getJobIdentifier() {
        return jobIdentifier;
    }

    public AgentStatusReportExecutor executor(PluginRequest pluginRequest, AgentInstances agentInstances, ViewBuilder viewBuilder) {
        return new AgentStatusReportExecutor(this, pluginRequest, agentInstances, viewBuilder);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AgentStatusReportRequest that = (AgentStatusReportRequest) o;
        return Objects.equals(elasticAgentId, that.elasticAgentId) &&
                Objects.equals(jobIdentifier, that.jobIdentifier);
    }

    @Override
    public int hashCode() {
        return Objects.hash(elasticAgentId, jobIdentifier);
    }

    @Override
    public String toString() {
        return "AgentStatusReportRequest{" +
                "elasticAgentId='" + elasticAgentId + '\'' +
                ", jobIdentifier=" + jobIdentifier +
                '}';
    }
}
