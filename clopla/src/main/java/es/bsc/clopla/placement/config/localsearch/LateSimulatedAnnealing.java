/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */

package es.bsc.clopla.placement.config.localsearch;

import org.optaplanner.core.config.localsearch.decider.acceptor.AcceptorConfig;

/**
 * Late simulated annealing algorithm.
 *
 * @author Mario Macias (github.com/mariomac), David Ortiz (david.ortiz@bsc.es)
 */
public class LateSimulatedAnnealing extends LocalSearch {

    private final int lateSimulatedAnnealingSize;

    public LateSimulatedAnnealing(int lateSimulatedAnnealingSize, int acceptedCountLimit) {
        this.lateSimulatedAnnealingSize = lateSimulatedAnnealingSize;
        this.acceptedCountLimit = acceptedCountLimit;
    }

    @Override
    public AcceptorConfig getAcceptorConfig() {
        AcceptorConfig result = new AcceptorConfig();
        result.setLateSimulatedAnnealingSize(lateSimulatedAnnealingSize);
        return result;
    }

}
