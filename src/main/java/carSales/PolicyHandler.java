package carSales;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

import carSales.config.kafka.KafkaProcessor;

@Service
public class PolicyHandler{
	
	@Autowired
	InsuranceRepository insuranceRepository;
    
    @StreamListener(KafkaProcessor.INPUT)
    public void wheneverSold_Insure(@Payload Sold sold){
    	
    	Integer carYear = sold.getCarYear();
    	Integer carAccidentCnt = sold.getCarAccidentCnt();
    	Integer drivingYear = sold.getDrivingYear();
    	Integer salesAmount = sold.getSalesAmount();
    	Integer insureRate = 0;
    	Integer insureAmount = 0;    	

        if(sold.isMe()){
        	
        	if (carYear > 20) {
        		insureRate += 3;
        	}
        	else if (carYear <= 20 & carYear > 10) {
        		insureRate += 2;
        	}
        	else {
        		insureRate += 1;
        	}
        	
        	if ( carAccidentCnt > 10 ) {
        		insureRate += 3; 
        	}
        	else if ( carAccidentCnt > 5 ) {
        		insureRate += 2;
        	}
        	else if ( carAccidentCnt > 0 ) {
        		insureRate += 1;
        	}
        	
    		if ( drivingYear > 20 ) { 
    			insureRate += 1;
     		}
    		else if ( drivingYear <= 20 & drivingYear > 10 ) { 
    			insureRate += 2;
    		}
    		else {
    			insureRate += 3;
    		}
    		
    		insureAmount = Math.round( salesAmount * insureRate / 100 );
    		
    		Insurance insurance = new Insurance();
        	insurance.setCarId(sold.getId());
        	insurance.setSalesAmount(salesAmount);
        	insurance.setInsureRate(insureRate);
        	insurance.setInsureAmount(insureAmount);
        	insurance.setStatus("insured");
        	
        	System.out.println("jskdfj;aslkdjfaksldjfklas;djfklasjdfklasjdfkljasdfkjsd;flks");
        	insuranceRepository.save(insurance);
        	
        }
    }

}
