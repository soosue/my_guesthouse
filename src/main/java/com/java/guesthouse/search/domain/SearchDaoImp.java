package com.java.guesthouse.search.domain;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Component;

import com.java.guesthouse.aop.HomeAspect;
import com.java.guesthouse.exfile.dto.ExFileDto;
import com.java.guesthouse.experience.service.dto.ExperienceImgDto;
import com.java.guesthouse.file.dto.FileDto;
import com.java.guesthouse.host.service.dto.HostDto;
import com.java.guesthouse.host.service.dto.HostImgDto;
import com.java.guesthouse.search.service.dto.GetCountDto;

@Component
public class SearchDaoImp implements SearchDao {

    private final SqlSessionTemplate session;

    public SearchDaoImp(SqlSessionTemplate session) {
        this.session = session;
    }

    @Override
    public List<HostImgDto> searchHouse(Map<String, Object> dataMap) {
        HomeAspect.logger.info(HomeAspect.logMsg + "sort: " + dataMap.get("sort"));
        List<HostImgDto> hostImgList = session.selectList("dao.searchMapper.searchHouse", dataMap);
        for (HostImgDto hostImgDto : hostImgList) {
            List<FileDto> fileList = session.selectList("dao.searchMapper.getHouseImg", hostImgDto.getHouseCode());
            hostImgDto.setFileList(fileList);
        }
        return hostImgList;
    }

    @Override
    public GetCountDto getCount(Map<String, Object> dataMap) {
        GetCountDto getCountDto = session.selectOne("dao.searchMapper.getCount", dataMap);
        //System.out.println(getCountDto.getMax()+","+getCountDto.getMin());

        return getCountDto;
    }

    //테스트 용으로 데이터 넣기 위한 함수
    @Override
    public int dataInputOk(HostDto hostDto) {
        return session.insert("dao.searchMapper.dataInput", hostDto);
    }

    @Override
    public HostImgDto overlay(int houseCode, Integer memberCode) {
        Map<String, Integer> map = new HashMap<String, Integer>();
        map.put("houseCode", houseCode);
        map.put("memberCode", memberCode);

        HostImgDto hostImgDto = session.selectOne("dao.searchMapper.overlay", map);
        List<FileDto> fileList = session.selectList("dao.searchMapper.getHouseImg", houseCode);
        hostImgDto.setFileList(fileList);
        HomeAspect.logger.info(HomeAspect.logMsg + "ajax Dto: " + hostImgDto);

        return hostImgDto;
    }

    @Override
    public List<ExperienceImgDto> searchEx(Map<String, Object> dataMap) {
        HomeAspect.logger.info(HomeAspect.logMsg + "sort: " + dataMap.get("sort"));

        //체험들을 가져와서
        List<ExperienceImgDto> exImgList = session.selectList("dao.searchMapper.searchEx", dataMap);


        for (ExperienceImgDto exImgDto : exImgList) {
            List<ExFileDto> exFileList = session.selectList("dao.searchMapper.getExImg", exImgDto.getExCode());
            exImgDto.setExFileList(exFileList);
        }
        return exImgList;
    }

    @Override
    public int getExCount(Map<String, Object> dataMap) {
        return session.selectOne("dao.searchMapper.getExCount", dataMap);
    }

    @Override
    public ExperienceImgDto exOverlay(int exCode, Integer memberCode) {
        Map<String, Integer> map = new HashMap<String, Integer>();
        map.put("exCode", exCode);
        map.put("memberCode", memberCode);

        ExperienceImgDto exImgDto = session.selectOne("dao.searchMapper.exOverlay", map);
        List<ExFileDto> exFileList = session.selectList("dao.searchMapper.getExImg", exCode);
        exImgDto.setExFileList(exFileList);
        HomeAspect.logger.info(HomeAspect.logMsg + "ajax Dto: " + exImgDto);

        return exImgDto;
    }


}
