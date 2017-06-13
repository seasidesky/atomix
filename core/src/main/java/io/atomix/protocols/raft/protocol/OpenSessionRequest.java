/*
 * Copyright 2017-present Open Networking Laboratory
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package io.atomix.protocols.raft.protocol;

import java.util.Objects;

import static com.google.common.base.MoreObjects.toStringHelper;
import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Open session request.
 */
public class OpenSessionRequest extends AbstractRaftRequest {

  /**
   * Returns a new open session request builder.
   *
   * @return A new open session request builder.
   */
  public static Builder builder() {
    return new Builder();
  }

  private final String client;
  private final String name;
  private final String stateMachine;
  private final long timeout;

  public OpenSessionRequest(String client, String name, String stateMachine, long timeout) {
    this.client = client;
    this.name = name;
    this.stateMachine = stateMachine;
    this.timeout = timeout;
  }

  @Override
  public Type type() {
    return Type.OPEN_SESSION;
  }

  /**
   * Returns the client identifier.
   *
   * @return The client identifier.
   */
  public String client() {
    return client;
  }

  /**
   * Returns the state machine name.
   *
   * @return The state machine name.
   */
  public String name() {
    return name;
  }

  /**
   * Returns the state machine type;
   *
   * @return The state machine type.
   */
  public String stateMachine() {
    return stateMachine;
  }

  /**
   * Returns the session timeout.
   *
   * @return The session timeout.
   */
  public long timeout() {
    return timeout;
  }

  @Override
  public int hashCode() {
    return Objects.hash(getClass(), name, stateMachine, timeout);
  }

  @Override
  public boolean equals(Object object) {
    if (object instanceof OpenSessionRequest) {
      OpenSessionRequest request = (OpenSessionRequest) object;
      return request.name.equals(name)
              && request.stateMachine.equals(stateMachine)
              && request.timeout == timeout;
    }
    return false;
  }

  @Override
  public String toString() {
    return toStringHelper(this)
            .add("client", client)
            .add("name", name)
            .add("stateMachine", stateMachine)
            .add("timeout", timeout)
            .toString();
  }

  /**
   * Open session request builder.
   */
  public static class Builder extends AbstractRaftRequest.Builder<Builder, OpenSessionRequest> {
    private String client;
    private String name;
    private String stateMachine;
    private long timeout;

    /**
     * Sets the client identifier.
     *
     * @param client The client identifier.
     * @return The open session request builder.
     * @throws NullPointerException if {@code client} is {@code null}
     */
    public Builder withClient(String client) {
      this.client = checkNotNull(client, "client");
      return this;
    }

    /**
     * Sets the state machine name.
     *
     * @param name The state machine name.
     * @return The open session request builder.
     * @throws NullPointerException if {@code name} is {@code null}
     */
    public Builder withName(String name) {
      this.name = checkNotNull(name, "name");
      return this;
    }

    /**
     * Sets the state machine type.
     *
     * @param stateMachine The state machine type.
     * @return The open session request builder.
     * @throws NullPointerException if {@code type} is {@code null}
     */
    public Builder withStateMachine(String stateMachine) {
      this.stateMachine = checkNotNull(stateMachine, "stateMachine");
      return this;
    }

    /**
     * Sets the session timeout.
     *
     * @param timeout The session timeout.
     * @return The open session request builder.
     * @throws IllegalArgumentException if {@code timeout} is not positive
     */
    public Builder withTimeout(long timeout) {
      checkArgument(timeout >= 0, "timeout must be positive");
      this.timeout = timeout;
      return this;
    }

    @Override
    protected void validate() {
      super.validate();
      checkNotNull(client, "client");
      checkNotNull(name, "name");
      checkNotNull(stateMachine, "stateMachine");
      checkArgument(timeout >= 0, "timeout must be positive");
    }

    /**
     * @throws IllegalStateException is session is not positive
     */
    @Override
    public OpenSessionRequest build() {
      validate();
      return new OpenSessionRequest(client, name, stateMachine, timeout);
    }
  }
}