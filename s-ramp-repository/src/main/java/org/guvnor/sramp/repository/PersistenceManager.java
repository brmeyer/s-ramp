package org.guvnor.sramp.repository;

import java.io.InputStream;

import org.s_ramp.xmlns._2010.s_ramp.BaseArtifactType;

public interface PersistenceManager {

    public String persistArtifact(String name, InputStream artifact) throws UnsupportedFiletypeException;
    
    public String persistDerivedArtifact(BaseArtifactType baseArtifactType);
}