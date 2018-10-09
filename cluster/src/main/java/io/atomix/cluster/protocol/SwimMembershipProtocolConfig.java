/*
 * Copyright 2018-present Open Networking Foundation
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package io.atomix.cluster.protocol;

import java.time.Duration;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;

/**
 * SWIM membership protocol configuration.
 */
public class SwimMembershipProtocolConfig extends GroupMembershipProtocolConfig {
  private static final boolean DEFAULT_BROADCAST_UPDATES = false;
  private static final boolean DEFAULT_BROADCAST_DISPUTES = true;
  private static final int DEFAULT_GOSSIP_INTERVAL = 250;
  private static final int DEFAULT_GOSSIP_FANOUT = 2;
  private static final int DEFAULT_PROBE_INTERVAL = 1000;
  private static final int DEFAULT_SUSPECT_PROBES = 3;
  private static final int DEFAULT_FAILURE_TIMEOUT = 10000;

  private boolean broadcastUpdates = DEFAULT_BROADCAST_UPDATES;
  private boolean broadcastDisputes = DEFAULT_BROADCAST_DISPUTES;
  private Duration gossipInterval = Duration.ofMillis(DEFAULT_GOSSIP_INTERVAL);
  private int gossipFanout = DEFAULT_GOSSIP_FANOUT;
  private Duration probeInterval = Duration.ofMillis(DEFAULT_PROBE_INTERVAL);
  private int suspectProbes = DEFAULT_SUSPECT_PROBES;
  private Duration failureTimeout = Duration.ofMillis(DEFAULT_FAILURE_TIMEOUT);

  /**
   * Returns whether to broadcast member updates to all peers.
   *
   * @return whether to broadcast member updates to all peers
   */
  public boolean isBroadcastUpdates() {
    return broadcastUpdates;
  }

  /**
   * Sets whether to broadcast member updates to all peers.
   *
   * @param broadcastUpdates whether to broadcast member updates to all peers
   * @return the protocol configuration
   */
  public SwimMembershipProtocolConfig setBroadcastUpdates(boolean broadcastUpdates) {
    this.broadcastUpdates = broadcastUpdates;
    return this;
  }

  /**
   * Returns whether to broadcast disputes to all peers.
   *
   * @return whether to broadcast disputes to all peers
   */
  public boolean isBroadcastDisputes() {
    return broadcastDisputes;
  }

  /**
   * Sets whether to broadcast disputes to all peers.
   *
   * @param broadcastDisputes whether to broadcast disputes to all peers
   * @return the protocol configuration
   */
  public SwimMembershipProtocolConfig setBroadcastDisputes(boolean broadcastDisputes) {
    this.broadcastDisputes = broadcastDisputes;
    return this;
  }

  /**
   * Returns the gossip interval.
   *
   * @return the gossip interval
   */
  public Duration getGossipInterval() {
    return gossipInterval;
  }

  /**
   * Sets the gossip interval.
   *
   * @param gossipInterval the gossip interval
   * @return the protocol configuration
   */
  public SwimMembershipProtocolConfig setGossipInterval(Duration gossipInterval) {
    this.gossipInterval = gossipInterval;
    return this;
  }

  /**
   * Returns the gossip fanout.
   *
   * @return the gossip fanout
   */
  public int getGossipFanout() {
    return gossipFanout;
  }

  /**
   * Sets the gossip fanout.
   *
   * @param gossipFanout the gossip fanout
   * @return the protocol configuration
   */
  public SwimMembershipProtocolConfig setGossipFanout(int gossipFanout) {
    checkArgument(gossipFanout > 0, "gossipFanout must be positive");
    this.gossipFanout = gossipFanout;
    return this;
  }

  /**
   * Returns the probe interval.
   *
   * @return the probe interval
   */
  public Duration getProbeInterval() {
    return probeInterval;
  }

  /**
   * Sets the probe interval.
   *
   * @param probeInterval the probe interval
   * @return the membership configuration
   */
  public SwimMembershipProtocolConfig setProbeInterval(Duration probeInterval) {
    checkNotNull(probeInterval, "probeInterval cannot be null");
    checkArgument(!probeInterval.isNegative() && !probeInterval.isZero(), "probeInterval must be positive");
    this.probeInterval = probeInterval;
    return this;
  }

  /**
   * Returns the number of probes to perform on suspect members.
   *
   * @return the number of probes to perform on suspect members
   */
  public int getSuspectProbes() {
    return suspectProbes;
  }

  /**
   * Sets the number of probes to perform on suspect members.
   *
   * @param suspectProbes the number of probes to perform on suspect members
   * @return the membership configuration
   */
  public SwimMembershipProtocolConfig setSuspectProbes(int suspectProbes) {
    checkArgument(suspectProbes > 0, "suspectProbes must be positive");
    this.suspectProbes = suspectProbes;
    return this;
  }

  /**
   * Returns the base failure timeout.
   *
   * @return the base failure timeout
   */
  public Duration getFailureTimeout() {
    return failureTimeout;
  }

  /**
   * Sets the base failure timeout.
   *
   * @param failureTimeout the base failure timeout
   * @return the group membership configuration
   */
  public SwimMembershipProtocolConfig setFailureTimeout(Duration failureTimeout) {
    checkNotNull(failureTimeout, "failureTimeout cannot be null");
    checkArgument(!failureTimeout.isNegative() && !failureTimeout.isZero(), "failureTimeout must be positive");
    this.failureTimeout = checkNotNull(failureTimeout);
    return this;
  }

  @Override
  public GroupMembershipProtocol.Type getType() {
    return SwimMembershipProtocol.TYPE;
  }
}