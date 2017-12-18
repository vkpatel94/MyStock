package stockapi;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.security.SecureRandom;
import java.security.cert.X509Certificate;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonReader;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import javax.servlet.http.HttpServletRequest;

import dao.dbconnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

@ManagedBean
@SessionScoped
public class StockApiBean {

    private static final long serialVersionUID = 1L;
    static final String API_KEY = "6R30USTN9U683IWZ";

    private String symbol;
    private double price;
    private int qty;
    private double amt;
    private String table1Markup;
    private String table2Markup;
    private String table3Markup = "";
    private String table4Markup = "";

    private String selectedSymbol;
    private List<SelectItem> availableSymbols;

    public String getPurchaseSymbol() {
        if (getRequestParameter("symbol") != null) {
            symbol = getRequestParameter("symbol");
        }
        return symbol;
    }
    
    public void setPurchaseSymbol(String purchaseSymbol) {
        System.out.println("func setPurchaseSymbol()");  //check
    }

    public double getPurchasePrice() {
        if (getRequestParameter("price") != null) {
            price = Double.parseDouble(getRequestParameter("price"));
            System.out.println("price: " + price);
        }
        return price;
    }

    public void setPurchasePrice(double purchasePrice) {
        System.out.println("func setPurchasePrice()");  //check
    }
    
    private String getRequestParameter(String name) {
        return ((HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest()).getParameter(name);
    }

    @PostConstruct
    public void init() {
        //initially populate stock list
        availableSymbols = new ArrayList<SelectItem>();
        availableSymbols.add(new SelectItem("AAPL", "Apple"));
        availableSymbols.add(new SelectItem("AMZN", "Amazon LLC"));
        availableSymbols.add(new SelectItem("AR", "Antero Resources"));
        availableSymbols.add(new SelectItem("EBAY", "Ebay"));
        availableSymbols.add(new SelectItem("FB", "Facebook, Inc."));
        availableSymbols.add(new SelectItem("GOLD", "Gold"));
        availableSymbols.add(new SelectItem("GOOGL", "Google"));
        availableSymbols.add(new SelectItem("MSFT", "Microsoft"));
        availableSymbols.add(new SelectItem("SLV", "Silver"));
        availableSymbols.add(new SelectItem("TWTR", "Twitter, Inc."));

        //initially populate intervals for stock api
        availableIntervals = new ArrayList<SelectItem>();
        availableIntervals.add(new SelectItem("1min", "1min"));
        availableIntervals.add(new SelectItem("5min", "5min"));
        availableIntervals.add(new SelectItem("15min", "15min"));
        availableIntervals.add(new SelectItem("30min", "30min"));
        availableIntervals.add(new SelectItem("60min", "60min"));
    }

    private String selectedInterval;
    private List<SelectItem> availableIntervals;

    public String getSelectedInterval() {
        return selectedInterval;
    }

    public void setSelectedInterval(String selectedInterval) {
        this.selectedInterval = selectedInterval;
    }

    public List<SelectItem> getAvailableIntervals() {
        return availableIntervals;
    }

    public void setAvailableIntervals(List<SelectItem> availableIntervals) {
        this.availableIntervals = availableIntervals;
    }

    public String getSelectedSymbol() {
        return selectedSymbol;
    }

    public void setSelectedSymbol(String selectedSymbol) {
        this.selectedSymbol = selectedSymbol;
    }

    public List<SelectItem> getAvailableSymbols() {
        return availableSymbols;
    }

    public void setAvailableSymbols(List<SelectItem> availableSymbols) {
        this.availableSymbols = availableSymbols;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    public double getAmt() {
        return amt;
    }

    public void setAmt(double amt) {
        this.amt = amt;
    }

    public String getTable1Markup() {
        return table1Markup;
    }

    public void setTable1Markup(String table1Markup) {
        this.table1Markup = table1Markup;
    }

    public String getTable2Markup() {
        return table2Markup;
    }

    public void setTable2Markup(String table2Markup) {
        this.table2Markup = table2Markup;
    }
    
    public String getTable3Markup() {
		return table3Markup;
	}

	public void setTable3Markup(String table3Markup) {
		this.table3Markup = table3Markup;
	}
	
	public String getTable4Markup() {
		return table4Markup;
	}

	public void setTable4Markup(String table4Markup) {
		this.table4Markup = table4Markup;
	}

	public String createDbRecord(String symbol, double price, int qty, double amt) {
        try {
            //System.out.println("symbol: " + this.symbol + ", price: " + this.price + "\n");
            //System.out.println("qty: " + this.qty + ", amt: " + this.amt + "\n");

            Connection con = dbconnection.Open();
            Statement statement = con.createStatement();
            
            //get userid
            Integer uid = (Integer) FacesContext.getCurrentInstance()
                    .getExternalContext()
                    .getSessionMap().get("uid");
            
            System.out.println(uid);
            System.out.println("symbol:" + symbol);
            System.out.println("price:" + price);
            System.out.println("qty:" + qty);
            System.out.println("amt:" + amt);
            statement.executeUpdate("INSERT INTO `purchase` (`id`, `uid`, `stock_symbol`, `qty`, `price`, `amt`) "
                    + "VALUES (NULL,'" + uid + "','" + symbol + "','" + qty + "','" + price + "','" + amt +"')");
            
//            statement.close();
//            con.close();
            FacesContext.getCurrentInstance().addMessage(null,new FacesMessage(FacesMessage.SEVERITY_INFO, "Successfully purchased stock",""));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return "purchase";
    }
    
    public String watchlistRecord() {
        try {
            //System.out.println("symbol: " + this.symbol + ", price: " + this.price + "\n");
            //System.out.println("qty: " + this.qty + ", amt: " + this.amt + "\n");

            Connection con = dbconnection.Open();
            Statement statement = con.createStatement();
            
            //get userid
            Integer uid = (Integer) FacesContext.getCurrentInstance()
                    .getExternalContext()
                    .getSessionMap().get("uid");
            
//            System.out.println(uid);
//            System.out.println("symbol:" + symbol);
            statement.executeUpdate("INSERT INTO watchlist (`id`, `uid`, `stock_symbol`) "
                    + "VALUES (NULL,'" + uid + "','" + selectedSymbol + "')");
            
//            statement.close();
//            con.close();
            FacesContext.getCurrentInstance().addMessage(null,new FacesMessage(FacesMessage.SEVERITY_INFO, "Successfully added to watchlist",""));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return "UserDashboard";
    }
    
    public String viewWatchlist() throws MalformedURLException, IOException {
        

        	Integer uid = (Integer) FacesContext.getCurrentInstance()
                    .getExternalContext()
                    .getSessionMap().get("uid");
        	System.out.println("uid  "+uid);
         try {
            Connection con = dbconnection.Open();
            PreparedStatement statement =  con.prepareStatement("SELECT * FROM watchlist where uid = uid");
            
            int i=0;
            //get userid
            String close="";
            
            System.out.println(uid);
//            System.out.println("symbol:" + symbol);
            ResultSet rs = statement.executeQuery();
            this.table3Markup += "";
        	this.table3Markup += "<table class='table table-hover'>";
            this.table3Markup += "<thead><tr><th>Number</th><th>Stock Symbol</th><th>Close</th></tr></thead>";
            this.table3Markup+="<tbody>";
           
            while(rs.next()) {
            String symbol = rs.getString("stock_symbol");
            String interval = "1min";
            String url = "https://www.alphavantage.co/query?function=TIME_SERIES_INTRADAY&symbol=" + symbol + "&interval=" + interval + "&apikey=" + API_KEY;
//            
//            this.table3Markup += "URL::: <a href='" + url + "'>Data Link</a><br>";
            InputStream inputStream = new URL(url).openStream();
//
//            // convert the json string back to object
            JsonReader jsonReader = Json.createReader(inputStream);
            JsonObject mainJsonObj = jsonReader.readObject();
            for(String key : mainJsonObj.keySet()) {
            	if (key.equals("Time Series (1min)")) {
            		JsonObject dataJsonObj = mainJsonObj.getJsonObject(key);
            		
            		  for(String subKey : dataJsonObj.keySet())
                      {
            			  JsonObject subJsonObj = dataJsonObj.getJsonObject(subKey);
            			  close=subJsonObj.getString("4. close");
            			  break;
                      }
            	}
//            		
            }
            	 
               this.table3Markup += "<tr>"+"<td>"+i+"</td>"+"<td>"+rs.getString("stock_symbol")+"</td>"+"<td>"+close+"</td>";
            	i++;
            }
            this.table3Markup+="</tr>";
            this.table3Markup+="</tbody></table>";
//            statement.close();
//            con.close();
//            FacesContext.getCurrentInstance().addMessage(null,new FacesMessage(FacesMessage.SEVERITY_INFO, "Successfully added to watchlist",""));
//            dbconnection.Close();
            return "watchlist";
        } catch (SQLException e) {
            e.printStackTrace();
            return "";
        }
        
    }
    
    public String myStocklist(){
        try {

        	Integer uid = (Integer) FacesContext.getCurrentInstance()
                    .getExternalContext()
                    .getSessionMap().get("uid");
            
            Connection con = dbconnection.Open();
            PreparedStatement statement =  con.prepareStatement("SELECT * FROM purchase where uid = "+uid);
//            statement.setString(1, uid);
            int i=0;
            //get userid
            
            System.out.println(uid);
//            System.out.println("symbol:" + symbol);
            ResultSet rs = statement.executeQuery();
            String path = FacesContext.getCurrentInstance().getExternalContext().getRequestContextPath();
            	 
            this.table4Markup += "";
        	this.table4Markup += "<table class='table table-hover'>";
            this.table4Markup += "<thead><tr><th>Number</th><th>Stock Symbol</th><th>Qty</th><th>Sell</th></tr></thead>";
            this.table4Markup+="<tbody>";
            while(rs.next()) {
            	System.out.println("Stock symbol:"+rs.getString("stock_symbol"));
            	System.out.println("Qty:"+rs.getInt("qty"));
                this.table4Markup += "<tr>"+"<td>"+i+"</td>"+"<td>"+rs.getString("stock_symbol")+"</td>"+"<td>"+rs.getString("qty")+"</td>";
            	this.table4Markup += "<td><a class='btn btn-success' href='" + path + "/faces/SellStock.xhtml?symbol=" + rs.getString("stock_symbol") + "&qty=" + rs.getInt("qty") + "'>Sell Stock</a></td>";
                
            	i++;
            }
            this.table4Markup+="</tr>";
            this.table4Markup+="</tbody></table>";
//            statement.close();
//            con.close();
//            FacesContext.getCurrentInstance().addMessage(null,new FacesMessage(FacesMessage.SEVERITY_INFO, "Successfully added to watchlist",""));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return "MyStocks";
    }

    public void installAllTrustingManager() {
        TrustManager[] trustAllCerts;
        trustAllCerts = new TrustManager[]{new X509TrustManager() {
            public X509Certificate[] getAcceptedIssuers() {
                return null;
            }

            public void checkClientTrusted(X509Certificate[] certs, String authType) {
            }

            public void checkServerTrusted(X509Certificate[] certs, String authType) {
            }
        }};

        // Install the all-trusting trust manager
        try {
            SSLContext sc = SSLContext.getInstance("TLS");
            sc.init(null, trustAllCerts, new SecureRandom());
            HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());
        } catch (Exception e) {
            System.out.println("Exception :" + e);
        }
        return;
    }

    public void timeseries() throws MalformedURLException, IOException {

        installAllTrustingManager();

        //System.out.println("selectedItem: " + this.selectedSymbol);
        //System.out.println("selectedInterval: " + this.selectedInterval);
        String symbol = this.selectedSymbol;
        String interval = this.selectedInterval;
        String url = "https://www.alphavantage.co/query?function=TIME_SERIES_INTRADAY&symbol=" + symbol + "&interval=" + interval + "&apikey=" + API_KEY;

        this.table1Markup += "URL::: <a href='" + url + "'>Data Link</a><br>";
        InputStream inputStream = new URL(url).openStream();

        // convert the json string back to object
        JsonReader jsonReader = Json.createReader(inputStream);
        JsonObject mainJsonObj = jsonReader.readObject();
        for (String key : mainJsonObj.keySet()) {
            if (key.equals("Meta Data")) {
                this.table1Markup = null; // reset table 1 markup before repopulating
                JsonObject jsob = (JsonObject) mainJsonObj.get(key);
                this.table1Markup += "<style>#detail >tbody > tr > td{ text-align:center;}</style><b>Stock Details</b>:<br>";
                this.table1Markup += "<table>";
                this.table1Markup += "<tr><td>Information</td><td>" + jsob.getString("1. Information") + "</td></tr>";
                this.table1Markup += "<tr><td>Symbol</td><td>" + jsob.getString("2. Symbol") + "</td></tr>";
                this.table1Markup += "<tr><td>Last Refreshed</td><td>" + jsob.getString("3. Last Refreshed") + "</td></tr>";
                this.table1Markup += "<tr><td>Interval</td><td>" + jsob.getString("4. Interval") + "</td></tr>";
                this.table1Markup += "<tr><td>Output Size</td><td>" + jsob.getString("5. Output Size") + "</td></tr>";
                this.table1Markup += "<tr><td>Time Zone</td><td>" + jsob.getString("6. Time Zone") + "</td></tr>";
                this.table1Markup += "</table>";
            } else {
                this.table2Markup = null; // reset table 2 markup before repopulating
                JsonObject dataJsonObj = mainJsonObj.getJsonObject(key);
                this.table2Markup += "<table class='table table-hover'>";
                this.table2Markup += "<thead><tr><th>Timestamp</th><th>Open</th><th>High</th><th>Low</th><th>Close</th><th>Volume</th></tr></thead>";
                this.table2Markup += "<tbody>";
                int i = 0;
                for (String subKey : dataJsonObj.keySet()) {
                    JsonObject subJsonObj = dataJsonObj.getJsonObject(subKey);
                    this.table2Markup
                            += "<tr>"
                            + "<td>" + subKey + "</td>"
                            + "<td>" + subJsonObj.getString("1. open") + "</td>"
                            + "<td>" + subJsonObj.getString("2. high") + "</td>"
                            + "<td>" + subJsonObj.getString("3. low") + "</td>"
                            + "<td>" + subJsonObj.getString("4. close") + "</td>"
                            + "<td>" + subJsonObj.getString("5. volume") + "</td>";
                    if (i == 0) {
                        String path = FacesContext.getCurrentInstance().getExternalContext().getRequestContextPath();
                        this.table2Markup += "<td><a class='btn btn-success' href='" + path + "/faces/purchase.xhtml?symbol=" + symbol + "&price=" + subJsonObj.getString("4. close") + "'>Buy Stock</a></td>";
//                        this.table2Markup += "<td><a class='btn btn-primary' href='" + path + "/faces/purchase.xhtml?symbol=" + symbol + "&price=" + subJsonObj.getString("4. close") + "'>Add to Watchlist</a></td>";
                    }
                    this.table2Markup += "</tr>";
                    i++;
                }
                this.table2Markup += "</tbody></table>";
            }
        }
        return;
    }

    public void purchaseStock() {
        System.out.println("Calling function purchaseStock()");
        System.out.println("stockSymbol: " + FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("stockSymbol"));
        System.out.println("stockPrice" + FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("stockPrice"));
        return;
    }
}
