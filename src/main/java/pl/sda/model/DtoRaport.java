package pl.sda.model;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class DtoRaport {
    @NotNull
    @Size(min = 10, message = "wpisz date początkową")
    private String dateFrom;
    @NotNull
    @Size(min = 10, message = "wpisz date końcową")
    private String dateTo;

    public String getDateFrom() {
        return dateFrom;
    }

    public void setDateFrom(String dateFrom) {
        this.dateFrom = dateFrom;
    }

    public String getDateTo() {
        return dateTo;
    }

    public void setDateTo(String dateTo) {
        this.dateTo = dateTo;
    }
}
