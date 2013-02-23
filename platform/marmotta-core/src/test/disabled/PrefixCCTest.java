/**
 * Copyright (C) 2013 The Apache Software Foundation.
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
package org.apache.marmotta.platform.core.services.prefix;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class PrefixCCTest {

    private PrefixCC prefixcc;
    private final String PREFIX = "foaf";
    private final String NS = "http://xmlns.com/foaf/0.1/";

    @Before
    public void setup() {
        prefixcc = new PrefixCC();
    }

    @After
    public void shutdown() {
        prefixcc = null;
    }

    @Test
    public void prefixTest() {
        assertEquals(NS, prefixcc.getNamespace(PREFIX));
    }

    @Test
    public void prefixFailTest() {
        assertNull(prefixcc.getPrefix("http://ewmknnakjfdajas.com/fsjaff#"));
    }

    @Test
    public void reverseTest() {
        assertEquals(PREFIX, prefixcc.getPrefix(NS));
    }

    @Test
    public void reverseFailTest() {
        assertNull(prefixcc.getPrefix("ewmknnakjfdajas"));
    }

}
