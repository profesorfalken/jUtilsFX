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

import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.TableView;
import javafx.util.Callback;
import org.jutils.jhardware.HardwareInfo;
import org.jutils.jhardware.model.ProcessorInfo;

/**
 * FXML Controller class
 *
 * @author Javier Garcia Alonso
 */
public class JHardwareFXController implements Initializable {

    @FXML
    private TableView propertiesTable;

    @FXML
    private TableColumn propertyColumn;

    @FXML
    private TableColumn valueColumn;

    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ObservableList<ObservableList<String>> tableItems = FXCollections.observableArrayList();

        ProcessorInfo cpuInfo = HardwareInfo.getProcessorInfo();
        tableItems.add(FXCollections.observableArrayList("Family", cpuInfo.getFamily()));
        tableItems.add(FXCollections.observableArrayList("Speed Mhz", cpuInfo.getMhz()));
        tableItems.add(FXCollections.observableArrayList("Model", cpuInfo.getModel()));
        tableItems.add(FXCollections.observableArrayList("Model Name", cpuInfo.getModelName()));
        tableItems.add(FXCollections.observableArrayList("Num cores", cpuInfo.getNumCores()));
        tableItems.add(FXCollections.observableArrayList("Stepping", cpuInfo.getStepping()));
        tableItems.add(FXCollections.observableArrayList("Temperature", cpuInfo.getTemperature()));
        tableItems.add(FXCollections.observableArrayList("Vendor Id", cpuInfo.getVendorId()));
        tableItems.add(FXCollections.observableArrayList("Cache Size", cpuInfo.getCacheSize()));
        //Here the code for the query
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
