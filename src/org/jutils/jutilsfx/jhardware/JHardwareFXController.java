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

import java.util.Map.Entry;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.TableView;
import javafx.util.Callback;
import org.jutils.jhardware.HardwareInfo;
import org.jutils.jhardware.model.BiosInfo;
import org.jutils.jhardware.model.MemoryInfo;
import org.jutils.jhardware.model.MotherboardInfo;
import org.jutils.jhardware.model.ProcessorInfo;

/**
 * FXML Controller class
 *
 * @author Javier Garcia Alonso
 */
public class JHardwareFXController {

    @FXML
    private TableView propertiesTable;

    @FXML
    private TableColumn propertyColumn;

    @FXML
    private TableColumn valueColumn;

    /**
     * Loads controller data
     *
     * @param hardwareType type of hardwareinfo to load
     */
    public void loadData(HardwareType hardwareType) {
        ObservableList<ObservableList<String>> tableItems = FXCollections.observableArrayList();

        switch (hardwareType) {
            case CPU:
                ProcessorInfo cpuInfo = HardwareInfo.getProcessorInfo();
                /*tableItems.add(FXCollections.observableArrayList("Family", cpuInfo.getFamily()));
                 tableItems.add(FXCollections.observableArrayList("Speed Mhz", cpuInfo.getMhz()));
                 tableItems.add(FXCollections.observableArrayList("Model", cpuInfo.getModel()));
                 tableItems.add(FXCollections.observableArrayList("Model Name", cpuInfo.getModelName()));
                 tableItems.add(FXCollections.observableArrayList("Num cores", cpuInfo.getNumCores()));
                 tableItems.add(FXCollections.observableArrayList("Stepping", cpuInfo.getStepping()));
                 tableItems.add(FXCollections.observableArrayList("Temperature", cpuInfo.getTemperature()));
                 tableItems.add(FXCollections.observableArrayList("Vendor Id", cpuInfo.getVendorId()));
                 tableItems.add(FXCollections.observableArrayList("Cache Size", cpuInfo.getCacheSize()));*/
                for (final Entry<String, String> entry : cpuInfo.getFullInfo().entrySet()) {
                    tableItems.add(FXCollections.observableArrayList(entry.getKey(), entry.getValue()));
                }
                break;
            case MEMORY:
                MemoryInfo memoryInfo = HardwareInfo.getMemoryInfo();
                /*tableItems.add(FXCollections.observableArrayList("Available Memory", memoryInfo.getAvailableMemory()));
                 tableItems.add(FXCollections.observableArrayList("Free Memory", memoryInfo.getFreeMemory()));
                 tableItems.add(FXCollections.observableArrayList("Total Memory", memoryInfo.getTotalMemory()));*/
                for (final Entry<String, String> entry : memoryInfo.getFullInfo().entrySet()) {
                    tableItems.add(FXCollections.observableArrayList(entry.getKey(), entry.getValue()));
                }
                break;
            case MOTHERBOARD:
                MotherboardInfo motherboardInfo = HardwareInfo.getMotherboardInfo();
                /*tableItems.add(FXCollections.observableArrayList("Manufacturer", motherboardInfo.getManufacturer()));
                 tableItems.add(FXCollections.observableArrayList("Name", motherboardInfo.getName()));
                 tableItems.add(FXCollections.observableArrayList("Version", motherboardInfo.getVersion()));*/
                for (final Entry<String, String> entry : motherboardInfo.getFullInfo().entrySet()) {
                    tableItems.add(FXCollections.observableArrayList(entry.getKey(), entry.getValue()));
                }
                break;
            case BIOS:
                BiosInfo biosInfo = HardwareInfo.getBiosInfo();
                /*tableItems.add(FXCollections.observableArrayList("Date", biosInfo.getDate()));
                 tableItems.add(FXCollections.observableArrayList("Manufacturer", biosInfo.getManufacturer()));
                 tableItems.add(FXCollections.observableArrayList("Version", biosInfo.getVersion()));*/
                for (final Entry<String, String> entry : biosInfo.getFullInfo().entrySet()) {
                    tableItems.add(FXCollections.observableArrayList(entry.getKey(), entry.getValue()));
                }
                break;
        }

        propertiesTable.setItems(tableItems);

        propertyColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<ObservableList<String>, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(CellDataFeatures<ObservableList<String>, String> cdf) {
                return new SimpleStringProperty(cdf.getValue().get(0));
            }
        });

        valueColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<ObservableList<String>, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(CellDataFeatures<ObservableList<String>, String> cdf) {
                return new SimpleStringProperty(cdf.getValue().get(1));
            }
        });
    }
}
