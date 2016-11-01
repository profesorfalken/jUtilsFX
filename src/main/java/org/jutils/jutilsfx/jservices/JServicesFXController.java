package org.jutils.jutilsfx.jservices;

import java.net.URL;
import java.util.ResourceBundle;

import org.jutils.jprocesses.JProcesses;
import org.jutils.jprocesses.model.ProcessInfo;

import com.profesorfalken.jservices.JServices;
import com.profesorfalken.jservices.model.ServiceInfo;

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

public class JServicesFXController implements Initializable {
	@FXML private TableView<ServiceInfo> servicesTable;
    @FXML private TableColumn<ServiceInfo, String> nameColumn;
    @FXML private TableColumn<ServiceInfo, String> statusNameColumn;
    
	public void initialize(URL location, ResourceBundle resources) {
		ObservableList<ServiceInfo> servicessList = FXCollections.observableArrayList(JServices.listAll());

		servicesTable.setItems(servicessList);

		nameColumn.setCellValueFactory(new Callback<CellDataFeatures<ServiceInfo, String>, ObservableValue<String>>() {
            public ObservableValue<String> call(CellDataFeatures<ServiceInfo, String> cdf) {
                return new SimpleStringProperty(cdf.getValue().getName());
            }
        });

		statusNameColumn.setCellValueFactory(new Callback<CellDataFeatures<ServiceInfo, String>, ObservableValue<String>>() {
            public ObservableValue<String> call(CellDataFeatures<ServiceInfo, String> cdf) {
                return new SimpleStringProperty(cdf.getValue().getStatus().toString());
            }
        });        
		
	}
    
    
}
