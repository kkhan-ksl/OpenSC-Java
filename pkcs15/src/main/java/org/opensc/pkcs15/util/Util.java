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
 * Created: 26.12.2008
 * 
 ***********************************************************/

package org.opensc.pkcs15.util;

import java.io.File;
import java.io.IOException;


/**
 * Static helper functions.
 * 
 * @author wglas
 */
public abstract class Util {

    /**
     * @param b A byte array to be formatted.
     * @return A string of length <code>b.length*2+b.length-1</code> consisting of
     *         hexadecimal representations of each byte in <code>b</code>
     *         separated by ':'.
     */
    static public String asHex(byte[] b) {
        
        if (b==null) return null;
        
        StringBuffer sb = new StringBuffer(b.length*3);
        
        for (int i=0;i<b.length;++i) {
            
            if (i>0) sb.append(':');
            
            int iv = ((int)b[i]) & 0xff;
            
            appendHexByte(sb,iv);
        }
        
        return sb.toString();
    }
    
    /**
     * @param b A byte array to be formatted.
     * @return A string of length <code>b.length*2+b.length-1</code> consisting of
     *         hexadecimal representations of each byte in <code>b</code>
     *         separated by ':'.
     */
    static public String asHexMask(int[] b) {
        
        if (b==null) return null;
        
        StringBuffer sb = new StringBuffer(b.length*6);
        
        for (int i=0;i<b.length;++i) {
            
            if (i>0) sb.append(':');
               
            int mask = (b[i]&0xff00) >> 8;
            
            if (mask == 0) {

                sb.append('*');
            }
            else {
                
                int iv = ((int)b[i]) & mask;
                appendHexByte(sb,iv);
                
                if (mask != 0xff) {
                    
                    sb.append('/');
                    appendHexByte(sb,mask);
                }
            }
        }
        
        return sb.toString();
    }
    
    /**
     * Recursively delete a directory.
     * 
     * @param dir The directory to delete. 
     * @throws IOException If the directory could not be deleted.
     */
    public static void rmdirRecursive(File dir) throws IOException {

        File[] children = dir.listFiles();
        
        for (File child : children) {
         
            if (child.isDirectory()) {
                if (!child.getName().equals(".") && !child.getName().equals(".."))
                    rmdirRecursive(child);
            }
            else {
                if (!child.delete())
                    throw new IOException("Cannot delete file ["+child+"].");
            }
        }
        
        if (!dir.delete())
            throw new IOException("Cannot delete directory ["+dir+"].");
    }

    /**
     * Append two hexadecimal digits to the given string buffer. 
     * 
     * @param sb The string buffer to append to.
     * @param b The byte value to append, stored in an integer.
     */
    public static void appendHexByte(StringBuffer sb, int b)
    {
        sb.append(Util.HEX_DIGITS[(b>>4)&0x0f]);
        sb.append(Util.HEX_DIGITS[b&0x0f]);
    }

    private static final char[] HEX_DIGITS =
    new char[] {'0','1','2','3','4','5','6','7','8','9','A','B','C','D','E','F'};
}
