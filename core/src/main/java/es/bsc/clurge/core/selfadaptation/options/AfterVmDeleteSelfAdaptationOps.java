/**
 Copyright (C) 2013-2014  Barcelona Supercomputing Center

 This library is free software; you can redistribute it and/or
 modify it under the terms of the GNU Lesser General Public
 License as published by the Free Software Foundation; either
 version 2.1 of the License, or (at your option) any later version.

 This library is distributed in the hope that it will be useful,
 but WITHOUT ANY WARRANTY; without even the implied warranty of
 MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 Lesser General Public License for more details.

 You should have received a copy of the GNU Lesser General Public
 License along with this library; if not, write to the Free Software
 Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301  USA
 */

package es.bsc.clurge.core.selfadaptation.options;

import es.bsc.clurge.core.models.scheduling.LocalSearchAlgorithmOptionsSet;

/**
 * @author David Ortiz Lopez (david.ortiz@bsc.es)
 */
public class AfterVmDeleteSelfAdaptationOps {

    private final LocalSearchAlgorithmOptionsSet localSearchAlgorithm;
    private final int maxExecTimeSeconds;

    public AfterVmDeleteSelfAdaptationOps(LocalSearchAlgorithmOptionsSet localSearchAlgorithm, int maxExecTimeSeconds) {
        this.localSearchAlgorithm = localSearchAlgorithm;
        this.maxExecTimeSeconds = maxExecTimeSeconds;
    }

    public LocalSearchAlgorithmOptionsSet getLocalSearchAlgorithm() {
        return localSearchAlgorithm;
    }

    public int getMaxExecTimeSeconds() {
        return maxExecTimeSeconds;
    }

    @Override
    public String toString() {
        return "{" +
                "localSearchAlgorithm=" + localSearchAlgorithm +
                ", maxExecTimeSeconds=" + maxExecTimeSeconds +
                '}';
    }
}
