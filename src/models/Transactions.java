package models;

public class Transactions {
    private int id;
    private int userId;
    private double amount;
    private String category;
    private String date;
    private String type;
    private String description;

    public Transactions(int userId, double amount, String category, String date, String type, String description){
        this.userId= userId;
        this.amount = amount;
        this.category = category;
        this.date = date;
        this.type = type;
        this.description = description;
    }
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public int getUserId() { return userId; }
    public void setUserId(int userId) { this.userId = userId; }

    public double getAmount() { return amount; }
    public void setAmount(double amount) { this.amount = amount; }

    public String getCategory() { return category; }
    public void setCategory(String category) { this.category = category; }

    public String getDate() { return date; }
    public void setDate(String date) { this.date = date; }

    public String getType() { return type; }
    public void setType(String type) { this.type = type; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

}
