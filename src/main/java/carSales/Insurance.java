package carSales;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.Table;

import org.springframework.beans.BeanUtils;

@Entity
@Table(name="Insurance_table")
public class Insurance {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;
    private Long carId;
    private Integer salesAmount;
    private Integer insureRate;
    private Integer insureAmount;
    private String status;

    @PrePersist
    public void onPrePersist(){
        Insured insured = new Insured();
        BeanUtils.copyProperties(this, insured);
        insured.publishAfterCommit();

    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    public Long getCarId() {
        return carId;
    }

    public void setCarId(Long carId) {
        this.carId = carId;
    }
    public Integer getSalesAmount() {
        return salesAmount;
    }

    public void setSalesAmount(Integer salesAmount) {
        this.salesAmount = salesAmount;
    }
    public Integer getInsureRate() {
        return insureRate;
    }

    public void setInsureRate(Integer insureRate) {
        this.insureRate = insureRate;
    }
    public Integer getInsureAmount() {
        return insureAmount;
    }

    public void setInsureAmount(Integer insureAmount) {
        this.insureAmount = insureAmount;
    }


    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }


}
