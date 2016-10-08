/*
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.jutils.jutilsfx.jhardware;

import java.util.Arrays;
import java.util.List;
import java.util.Map.Entry;

import org.jutils.jhardware.HardwareInfo;
import org.jutils.jhardware.model.BiosInfo;
import org.jutils.jhardware.model.Display;
import org.jutils.jhardware.model.DisplayInfo;
import org.jutils.jhardware.model.GraphicsCard;
import org.jutils.jhardware.model.GraphicsCardInfo;
import org.jutils.jhardware.model.MemoryInfo;
import org.jutils.jhardware.model.MotherboardInfo;
import org.jutils.jhardware.model.NetworkInfo;
import org.jutils.jhardware.model.NetworkInterfaceInfo;
import org.jutils.jhardware.model.OSInfo;
import org.jutils.jhardware.model.ProcessorInfo;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.TableView;
import javafx.util.Callback;
import javafx.util.Pair;

/**
 * FXML Controller class
 *
 * @author Javier Garcia Alonso
 */
public class JHardwareFXController {

    @FXML
    private TableView<Pair<String, String>> propertiesTable;

    @FXML
    private TableColumn<Pair<String, String>, String> propertyColumn;

    @FXML
    private TableColumn<Pair<String, String>, String> valueColumn;

    /**
     * Loads controller data
     *
     * @param hardwareType type of hardwareinfo to load
     */
    public void loadData(HardwareType hardwareType) {
        ObservableList<Pair<String, String>> tableItems = FXCollections.observableArrayList();
        
        switch (hardwareType) {
            case CPU:
                ProcessorInfo cpuInfo = HardwareInfo.getProcessorInfo();
                for (final Entry<String, String> entry : cpuInfo.getFullInfo().entrySet()) {
                	tableItems.add(new Pair<String, String>(entry.getKey(), entry.getValue()));
                }
                break;
            case MEMORY:
                MemoryInfo memoryInfo = HardwareInfo.getMemoryInfo();                
                for (final Entry<String, String> entry : memoryInfo.getFullInfo().entrySet()) {
                    tableItems.add(new Pair<String, String>(entry.getKey(), entry.getValue()));
                }
                break;
            case MOTHERBOARD:
                MotherboardInfo motherboardInfo = HardwareInfo.getMotherboardInfo();                
                for (final Entry<String, String> entry : motherboardInfo.getFullInfo().entrySet()) {
                    tableItems.add(new Pair<String, String>(entry.getKey(), entry.getValue()));
                }
                break;
            case BIOS:
                BiosInfo biosInfo = HardwareInfo.getBiosInfo();               
                for (final Entry<String, String> entry : biosInfo.getFullInfo().entrySet()) {
                    tableItems.add(new Pair<String, String>(entry.getKey(), entry.getValue()));
                }
                break;
            case OS:
                OSInfo osInfo = HardwareInfo.getOSInfo();
                for (final Entry<String, String> entry : osInfo.getFullInfo().entrySet()) {
                    tableItems.add(new Pair<String, String>(entry.getKey(), entry.getValue()));
                }
                break;
            case NETWORK:
                NetworkInfo networkInfo = HardwareInfo.getNetworkInfo();
                List<NetworkInterfaceInfo> networkInterfaces = networkInfo.getNetworkInterfaces();
                for (final NetworkInterfaceInfo networkInterface : networkInterfaces) {
                    tableItems.add(new Pair<String, String>("Name", networkInterface.getName()));
                    tableItems.add(new Pair<String, String>("Type", networkInterface.getType()));
                    tableItems.add(new Pair<String, String>("IP v4", networkInterface.getIpv4()));
                    tableItems.add(new Pair<String, String>("IP v6", networkInterface.getIpv6()));
                    tableItems.add(new Pair<String, String>("Received Bytes", networkInterface.getReceivedBytes()));
                    tableItems.add(new Pair<String, String>("Received Packets", networkInterface.getReceivedPackets()));
                    tableItems.add(new Pair<String, String>("Transmitted Bytes", networkInterface.getTransmittedBytes()));
                    tableItems.add(new Pair<String, String>("Transmitted Packets", networkInterface.getTransmittedPackets()));
                }
                break;
            case DISPLAY:
            	DisplayInfo displayInfo = HardwareInfo.getDisplayInfo();
            	List<Display> displayDevices = displayInfo.getDisplayDevices();
            	
            	for (final Display display : displayDevices) {
            		tableItems.add(new Pair<String, String>("Name", display.getName()));
            		tableItems.add(new Pair<String, String>("Current Resolution", display.getCurrentResolution()));
            		tableItems.add(new Pair<String, String>("Supported Resolutions", Arrays.toString(display.getSupportedResolutions())));
            		tableItems.add(new Pair<String, String>("Refresh Rate", display.getRefreshRate()));
            	}            	
                break;
            case GRAPHICS_CARD:
            	GraphicsCardInfo graphicsCardInfo = HardwareInfo.getGraphicsCardInfo();
            	List<GraphicsCard> graphicsCards = graphicsCardInfo.getGraphicsCards();
            	
            	for (final GraphicsCard graphicsCard : graphicsCards) {
            		tableItems.add(new Pair<String, String>("Name", graphicsCard.getName()));
            		tableItems.add(new Pair<String, String>("Chip Type", graphicsCard.getChipType()));
            		tableItems.add(new Pair<String, String>("DAC Type", graphicsCard.getDacType()));
            		tableItems.add(new Pair<String, String>("Device Type", graphicsCard.getDeviceType()));
            		tableItems.add(new Pair<String, String>("Fan Speed", graphicsCard.getFanSpeed()));
            		tableItems.add(new Pair<String, String>("Manufacturer", graphicsCard.getManufacturer()));
            		tableItems.add(new Pair<String, String>("Temperature", graphicsCard.getTemperature()));
            	}            	
                break;
        }

        propertiesTable.setItems(tableItems);

        propertyColumn.setCellValueFactory(new Callback<CellDataFeatures<Pair<String, String>, String>, ObservableValue<String>>() {
            public ObservableValue<String> call(CellDataFeatures<Pair<String, String>, String> cdf) {
                return new SimpleStringProperty(cdf.getValue().getKey());
            }
        });

        valueColumn.setCellValueFactory(new Callback<CellDataFeatures<Pair<String, String>, String>, ObservableValue<String>>() {
            public ObservableValue<String> call(CellDataFeatures<Pair<String, String>, String> cdf) {
                return new SimpleStringProperty(cdf.getValue().getValue());
            }
        });
    }   
}
