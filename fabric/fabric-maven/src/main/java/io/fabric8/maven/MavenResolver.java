/**
 *  Copyright 2005-2016 Red Hat, Inc.
 *
 *  Red Hat licenses this file to you under the Apache License, version
 *  2.0 (the "License"); you may not use this file except in compliance
 *  with the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or
 *  implied.  See the License for the specific language governing
 *  permissions and limitations under the License.
 */
package io.fabric8.maven;

import java.io.Closeable;
import java.io.File;
import java.io.IOException;
import java.util.List;

import io.fabric8.common.util.Filter;
import org.eclipse.aether.RepositoryException;
import org.eclipse.aether.RepositorySystem;
import org.eclipse.aether.RepositorySystemSession;
import org.eclipse.aether.artifact.Artifact;
import org.eclipse.aether.graph.Dependency;
import org.eclipse.aether.graph.DependencyNode;
import org.eclipse.aether.repository.LocalRepository;
import org.eclipse.aether.repository.RemoteRepository;

public interface MavenResolver extends Closeable {

    /**
     * Access to the RepositorySystem
     */
    RepositorySystem getRepositorySystem();

    /**
     * Create a new session
     */
    RepositorySystemSession createSession();

    /**
     * Retrieve the list of configured repositories
     */
    List<RemoteRepository> getRepositories();

    /**
     * Retrieve the list of configured default repositories
     * @return
     */
    List<LocalRepository> getDefaultRepositories();

    /**
     * Resolve and download a maven based url
     */
    File download(String url) throws IOException;

    /**
     * Resolve and download a maven based url
     * Specifying <code>previousException</code> is a hint to resolver.
     */
    File download(String url, Exception previousException) throws IOException;

    /**
     * Resolve and download an artifact
     */
    File resolveFile(Artifact artifact) throws IOException;

    /**
     * Resolve and download an artifact
     * Specifying <code>previousException</code> is a hint to resolver.
     */
    File resolveFile(Artifact artifact, Exception previousException) throws IOException;

    /**
     * Build a tree of dependencies for the specified jar file
     */
    DependencyNode collectDependenciesForJar(File artifactFile, Filter<Dependency> excludeFilter) throws IOException, RepositoryException;

    /**
     * Returns {@link LocalRepository} used by the resolver
     * @return
     */
    File getLocalRepository();

    /**
     * Returns a hint about possible retry of operation that ended with <code>exception</code>
     * @param exception
     * @return
     */
    RetryChance isRetryableException(Exception exception);

    /**
     * Checks whether given {@link LocalRepository} can  handle snapshots
     * @param repo
     * @return
     */
    boolean handlesSnapshot(LocalRepository repo);

    /**
     * Enumeration of retry hints that may be used by client code when trying to repeat failed resolution attempt
     */
    enum RetryChance {
        NEVER(0),
        LOW(1),
        HIGH(2),
        UNKNOWN(Integer.MAX_VALUE);

        private int chance;

        RetryChance(int chance) {
            this.chance = chance;
        }

        /**
         * Ordering information for {@link RetryChance chances of retry}
         * @return
         */
        public int chance() {
            return chance;
        }
    }

}
