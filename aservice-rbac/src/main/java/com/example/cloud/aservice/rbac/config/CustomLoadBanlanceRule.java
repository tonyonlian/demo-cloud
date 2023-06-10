package com.example.cloud.aservice.rbac.config;

import com.netflix.loadbalancer.ILoadBalancer;
import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.Server;

import java.util.List;


public class CustomLoadBanlanceRule implements IRule {


    private ILoadBalancer loadBalancer;
    @Override
    public Server choose(Object o) {
        List<Server> allServers = loadBalancer.getAllServers();
        for (Server server : allServers) {
            System.out.println("=========serverAndPort: " +server.getHostPort());

        }
        return allServers.get(0);
    }

    @Override
    public void setLoadBalancer(ILoadBalancer iLoadBalancer) {
        this.loadBalancer = iLoadBalancer;
    }

    @Override
    public ILoadBalancer getLoadBalancer() {
        return loadBalancer;
    }
}
