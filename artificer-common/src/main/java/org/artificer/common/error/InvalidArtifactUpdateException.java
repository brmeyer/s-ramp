/*
 * Copyright 2014 JBoss Inc
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.artificer.common.error;


/**
 * Exception thrown when the user attempts to update an artifact incorrectly (for example
 * trying to update a non-document style artifact).
 *
 * @author eric.wittmann@redhat.com
 */
public class InvalidArtifactUpdateException extends ArtificerUserException {

    private static final long serialVersionUID = -2343995516760599930L;

    /**
     * Constructor.
     */
    public InvalidArtifactUpdateException(String message) {
        super(message);
    }
}
