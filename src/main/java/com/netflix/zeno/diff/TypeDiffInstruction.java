/*
 *
 *  Copyright 2013 Netflix, Inc.
 *
 *     Licensed under the Apache License, Version 2.0 (the "License");
 *     you may not use this file except in compliance with the License.
 *     You may obtain a copy of the License at
 *
 *         http://www.apache.org/licenses/LICENSE-2.0
 *
 *     Unless required by applicable law or agreed to in writing, software
 *     distributed under the License is distributed on an "AS IS" BASIS,
 *     WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *     See the License for the specific language governing permissions and
 *     limitations under the License.
 *
 */
package com.netflix.zeno.diff;


/**
 * The TypeDiffInstruction should be overridden to describe how find matching pairs of Objects of a given type.
 *
 * From each Object of this type, a primary key must be constructed/extracted.  This key must meaningfully override
 * hashCode() and equals(), and should be unique for a given type in the FastBlobStateEngine.
 *
 * The TypeDiffInstruction will automatically find pairs of Objects based on these primary keys, and each pair of Objects
 * will be traversed to find the diff.
 *
 * (from {@link DiffRecord})
 * Conceptually, The diff of two Objects is calculated by the following process:
 * 1) reduce all properties in each Object to sets of key/value pairs.
 * 2) pull out matching pairs of key/value pairs from both Objects.
 * 3) When there are no more matches left, the diff score between the two Objects is sum of the remaining key/value pairs for both Objects.
 *
 * @author dkoszewnik
 *
 */
public abstract class TypeDiffInstruction<T> {

    public abstract String getSerializerName();

    public abstract Object getKey(T object);

    @SuppressWarnings("unchecked")
    public Object getKeyFromObject(Object obj) {
        return getKey((T)obj);
    }

}
