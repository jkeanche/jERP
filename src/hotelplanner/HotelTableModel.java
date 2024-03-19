
package hotelplanner;


import interfaces.PlannableAsset;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Codepal
 */

    // Custom table model to handle assets
 public class HotelTableModel extends DefaultTableModel {
        private  List<Object[]> data;
        private  String[] columnNames;
        private  List<PlannableAsset> assets;

        public HotelTableModel() {
            this.data = new ArrayList<>();
            this.assets = new ArrayList<>();
            this.columnNames = new String[32];
            // Initialize column names
            columnNames[0] = "Asset";
            for (int i = 1; i < columnNames.length; i++) {
                columnNames[i] = Integer.toString(i);
            }
        }
        
        
        
        
      
       public HotelTableModel(int daysInMonth, List<PlannableAsset> assets) {
          
              this.data = new ArrayList<>();
              this.assets = assets;
              
              for (PlannableAsset asset : assets) {
                data.add(new Object[]{asset.getCategory()});
                Object[] rowData = new Object[daysInMonth + 1];
                rowData[0] = asset.getName();
                int startColumn = asset.getStartDate().getDayOfMonth();
                int endColumn = asset.getEndDate().getDayOfMonth();
                for (int i = startColumn; i <= endColumn; i++) {
                    rowData[i] = asset;
                }
                data.add(rowData);
            }
            this.columnNames = new String[daysInMonth + 1];
            // Initialize column names
            columnNames[0] = "Asset";
            for (int i = 1; i < columnNames.length; i++) {
                columnNames[i] = Integer.toString(i);
            }
            
        }
       

        @Override
        public int getColumnCount() {
            return columnNames.length;
        }

        @Override
        public String getColumnName(int column) {
            return columnNames[column];
        }

        @Override
        public int getRowCount() {
            return data.size();
        }

        @Override
        public Object getValueAt(int row, int column) {
            return data.get(row)[column];
        }
    }