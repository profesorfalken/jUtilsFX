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
package org.jutils.jutilsfx.jprocesses;

import java.net.URL;
import java.util.ResourceBundle;

import org.jutils.jprocesses.JProcesses;
import org.jutils.jprocesses.model.ProcessInfo;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.TableView;
import javafx.util.Callback;

/**
 * FXML Controller class
 *
 * @author Javier Garcia Alonso
 */
public class JProcessesFXController implements Initializable {
    @FXML private TableView<ProcessInfo> processesTable;
    @FXML private TableColumn<ProcessInfo, String> pidColumn;
    @FXML private TableColumn<ProcessInfo, String> processNameColumn;
    @FXML private TableColumn<ProcessInfo, String> processTimeColumn;
    
    @FXML private Button btnKillProcess;
    
    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    public void initialize(URL url, ResourceBundle rb) {
        ObservableList<ProcessInfo> processesList = FXCollections.observableArrayList(JProcesses.getProcessList());

        processesTable.setItems(processesList);

        pidColumn.setCellValueFactory(new Callback<CellDataFeatures<ProcessInfo, String>, ObservableValue<String>>() {
            public ObservableValue<String> call(CellDataFeatures<ProcessInfo, String> cdf) {
                return new SimpleStringProperty(cdf.getValue().getPid());
            }
        });

        processNameColumn.setCellValueFactory(new Callback<CellDataFeatures<ProcessInfo, String>, ObservableValue<String>>() {
            public ObservableValue<String> call(CellDataFeatures<ProcessInfo, String> cdf) {
                return new SimpleStringProperty(cdf.getValue().getTime());
            }
        });
        
        processTimeColumn.setCellValueFactory(new Callback<CellDataFeatures<ProcessInfo, String>, ObservableValue<String>>() {
            public ObservableValue<String> call(CellDataFeatures<ProcessInfo, String> cdf) {
                return new SimpleStringProperty(cdf.getValue().getName());
            }
        });
    }    
    
    @FXML
    protected void handleBtnKillProcess(ActionEvent event) throws Exception {
        ProcessInfo selectedRow = processesTable.getSelectionModel().getSelectedItem();
        JProcesses.killProcess(Integer.parseInt(selectedRow.getPid()));
        
        processesTable.getItems().remove(selectedRow);        
    }
    
}
