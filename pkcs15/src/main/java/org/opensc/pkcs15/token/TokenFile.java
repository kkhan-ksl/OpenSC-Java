/***********************************************************
 * $Id$
 * 
 * PKCS#15 cryptographic provider of the opensc project.
 * http://www.opensc-project.org
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
 *
 * Created: 25.12.2007
 * 
 ***********************************************************/

package org.opensc.pkcs15.token;

/**
 * @author wglas
 *
 */
public abstract class TokenFile implements TokenFileAcl {

    private final TokenPath path;
    private final long size;
    private final int acUpdate;
    private final int acAppend;
    private final int acDeactivate;
    private final int acActivate;
    private final int acDelete;
    private final int acAdmin;
    
    /**
     * @param path
     * @param acUpdate
     * @param acAppend
     * @param acActivate
     * @param acDeactivate
     * @param acDelete
     * @param acAdmin
     */
    public TokenFile(TokenPath path, long size,
            int acUpdate, int acAppend, int acDeactivate,
            int acActivate, int acDelete, int acAdmin) {
        super();
        this.path = path;
        this.size = size;
        this.acUpdate = acUpdate;
        this.acAppend = acAppend;
        this.acDeactivate = acDeactivate;
        this.acActivate = acActivate;
        this.acDelete = acDelete;
        this.acAdmin = acAdmin;
    }

    public TokenFile(TokenPath path, long size, TokenFileAcl acl)
    {
        this.path = path;
        this.size = size;
        this.acUpdate = acl.getAcUpdate();
        this.acAppend = acl.getAcAppend();
        this.acDeactivate = acl.getAcDeactivate();
        this.acActivate = acl.getAcActivate();
        this.acDelete = acl.getAcDelete();
        this.acAdmin = acl.getAcAdmin();        
    }
    
    /**
     * @param visitor A GoF Visitor implementing a polymorphic functionality on files.
     */
    abstract void accept(TokenFileVisitor visitor);
    
    /**
     * @return the path
     */
    public TokenPath getPath() {
        return this.path;
    }


    /**
     * @return the size
     */
    public long getSize() {
        return this.size;
    }

    /* (non-Javadoc)
     * @see org.opensc.pkcs15.token.TokenFileAcl#getAcUpdate()
     */
    public int getAcUpdate() {
        return this.acUpdate;
    }


    /* (non-Javadoc)
     * @see org.opensc.pkcs15.token.TokenFileAcl#getAcAppend()
     */
    public int getAcAppend() {
        return this.acAppend;
    }


    /* (non-Javadoc)
     * @see org.opensc.pkcs15.token.TokenFileAcl#getAcActivate()
     */
    public int getAcActivate() {
        return this.acActivate;
    }


    /* (non-Javadoc)
     * @see org.opensc.pkcs15.token.TokenFileAcl#getAcDeactivate()
     */
    public int getAcDeactivate() {
        return this.acDeactivate;
    }


    /* (non-Javadoc)
     * @see org.opensc.pkcs15.token.TokenFileAcl#getAcDelete()
     */
    public int getAcDelete() {
        return this.acDelete;
    }


    /* (non-Javadoc)
     * @see org.opensc.pkcs15.token.TokenFileAcl#getAcAdmin()
     */
    public int getAcAdmin() {
        return this.acAdmin;
    }

 }
