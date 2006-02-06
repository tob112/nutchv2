/**
 * Copyright 2005 The Apache Software Foundation
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

package org.apache.nutch.util;

import org.apache.hadoop.io.*;
import org.apache.hadoop.conf.*;

import junit.framework.TestCase;

public class WritableTestUtils {

  /** Utility method for testing writables. */
  public static void testWritable(Writable before) throws Exception {
    testWritable(before, null);
  }

  /** Utility method for testing writables. */
  public static void testWritable(Writable before, Configuration conf)
      throws Exception {
    DataOutputBuffer dob = new DataOutputBuffer();
    before.write(dob);
    
    DataInputBuffer dib = new DataInputBuffer();
    dib.reset(dob.getData(), dob.getLength());
    
    Writable after = (Writable)before.getClass().newInstance();
    if (conf != null) {
      ((Configurable)after).setConf(conf);
    }
    after.readFields(dib);

    TestCase.assertEquals(before, after);
  }
	
}