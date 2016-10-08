/*
 * Copyright 2016 javier.
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
package org.jutils.jutilsfx.jhardware;

/**
 *
 * @author Javier Garcia Alonso
 */
public enum HardwareType {

    CPU, MEMORY, MOTHERBOARD, BIOS, OS, NETWORK, DISPLAY, GRAPHICS_CARD;

    public static HardwareType fromLabel(String label) {
        if ("Memory".equals(label)) {
            return MEMORY;
        } else if ("Motherboard".equals(label)) {
            return MOTHERBOARD;
        } else if ("Bios".equals(label)) {
            return BIOS;
        } else if ("OS".equals(label)) {
            return OS;
        } else if ("Network".equals(label)) {
            return NETWORK;
        } else if ("Display".equals(label)) {
            return DISPLAY;
        } else if ("Graphics Card".equals(label)) {
            return GRAPHICS_CARD;
        }
        //Default
        return CPU;
    }
}
