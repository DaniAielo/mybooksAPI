package br.com.jlgregorio.mybooks.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

import java.util.ArrayList;
import java.util.List;

public class CustomModelMapper {

    private static ModelMapper mapper = new ModelMapper();

    public static <Origin, Destination> Destination parseObject(Origin origin, Class<Destination> destination){
        return mapper.map(origin, destination);
    }

    public static <Origin, Destination> List<Destination> parseObjectList(List<Origin> origin, Class<Destination>destination){
        List<Destination> destinationList = new ArrayList<Destination>();
        for(Origin o : origin){
            destinationList.add(mapper.map(o, destination));
        }
        return destinationList;
    }


}
