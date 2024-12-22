package watchIt;

import java.io.Serializable;
import java.time.LocalDate;

public class Subscription implements Serializable {
    public enum enPlan {Basic, Standard, Premium, Non}

    private enPlan Plan;
    private float Price;

    private LocalDate StartDate;
    private int AllowedWatches;

    public LocalDate getStartDate() {
        return StartDate;
    }

    public int getAllowedWatches() {
        return AllowedWatches;
    }

    public float getPrice() {
        return Price;
    }

    public enPlan getPlan() {
        return Plan;
    }
    public void setPlan(enPlan plan) {
        Plan = plan;
        initializePlanDetails();
    }

    public Subscription(enPlan plan, LocalDate startDate) {
        Plan = plan;
        StartDate = startDate;
        initializePlanDetails();
    }

    public Subscription() {
        this.Plan = enPlan.Non;
        this.StartDate = LocalDate.now();
        initializePlanDetails();
    }


    private void setPrice() {
        switch (Plan) {
            case Premium -> Price = 150;
            case Standard -> Price = 100;
            case Basic -> Price = 50;
        }
    }
    private void setAllowedWatches() {

        switch (Plan) {
            case Premium -> AllowedWatches = 30;
            case Standard -> AllowedWatches = 10;
            case Basic -> AllowedWatches = 5;
        }
    }
    public static float getPrices(enPlan plan) {
        switch (plan) {
            case Premium -> {
                return 150;
            }
            case Standard -> {
                return 100;
            }
            case Basic -> {
                return 50;
            }
            default -> {
                return 0 ;
            }
        }
    }

    private void initializePlanDetails() {
        switch (Plan) {
            case Premium -> {
                this.Price = 150;
                this.AllowedWatches = 30;
            }
            case Standard -> {
                this.Price = 100;
                this.AllowedWatches = 10;
            }
            case Basic -> {
                this.Price = 50;
                this.AllowedWatches = 5;
            }
            case Non -> {
                this.Price = 0;
                this.AllowedWatches = 0;
            }
        }
    }
}
