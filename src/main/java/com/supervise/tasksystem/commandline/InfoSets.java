package com.supervise.tasksystem.commandline;

import com.supervise.tasksystem.model.Expert;
import com.supervise.tasksystem.model.Market;
import com.supervise.tasksystem.model.ProductType;
import com.supervise.tasksystem.service.InformationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component
public class InfoSets {
    @Autowired
    InformationService informationService;
    int[] expertIds=null;
    int[] marketIds=null;
    int[] productIds=null;
    List<ProductType> productTypes=null;

    public int[] getExpertIds() {
        if(expertIds==null){
            refresh();
        }
        return expertIds;
    }
    private void refresh(){
        List<Market> markets=informationService.getAllMarkets();
        marketIds=new int[markets.size()];
        for(int i=0;i<markets.size();i++){
            marketIds[i]=markets.get(i).getMarketId();
        }
        List<Expert> experts=informationService.getAllExperts();
        expertIds=new int[experts.size()];
        for(int i=0;i<experts.size();i++){
            expertIds[i]=experts.get(i).getExpertId();
        }
        productTypes=informationService.getAllProductType();
        productIds=new int[productTypes.size()];
        for(int i=0;i<productTypes.size();i++){
            productIds[i]=productTypes.get(i).getProductTypeId();
        }
    }

    public int[] getMarketIds() {
        if(marketIds==null){
            refresh();
        }
        return marketIds;
    }
    public int[] getProductIds(){
        if(productIds==null){
            refresh();
        }
        return productIds;
    }
    public List<ProductType> getProducts(){
        if(productTypes==null){
            refresh();
        }
        return productTypes;
    }
}
