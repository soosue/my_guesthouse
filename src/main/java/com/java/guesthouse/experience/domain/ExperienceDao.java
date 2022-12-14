package com.java.guesthouse.experience.domain;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import com.java.guesthouse.aop.HomeAspect;
import com.java.guesthouse.exfile.dto.ExFileDto;
import com.java.guesthouse.experience.service.dto.ExperienceDto;
import com.java.guesthouse.experience.service.dto.ExperienceMainDto;
import com.java.guesthouse.experience.service.dto.GuestHouseMainDto;
import com.java.guesthouse.exremain.dto.ExRemainDto;
import com.java.guesthouse.exreserve.dto.ExReserveDto;
import com.java.guesthouse.exreview.dto.ExReviewDto;
import com.java.guesthouse.exreview.dto.ExReviewListDto;
import com.java.guesthouse.file.dto.FileDto;
import com.java.guesthouse.host.service.dto.HostDto;
import com.java.guesthouse.member.service.dto.MemberDto;

@Repository
public class ExperienceDao {

    private final SqlSessionTemplate sqlSessionTemplate;

    public ExperienceDao(SqlSessionTemplate sqlSessionTemplate) {
        this.sqlSessionTemplate = sqlSessionTemplate;
    }

    public int subImgUpload(ExFileDto exFileDto) {

        return sqlSessionTemplate.insert("dao.ExperienceMapper.subImgUpload", exFileDto);
    }

    public int memberCode(String email) {
        return sqlSessionTemplate.selectOne("dao.ExperienceMapper.memberCode", email);
    }

    public int exHostRegister(ExperienceDto experienceDto) {
        return sqlSessionTemplate.insert("dao.ExperienceMapper.exHostRegister", experienceDto);
    }

    public int mainImgUpload(ExFileDto exFileDto) {
        return sqlSessionTemplate.insert("dao.ExperienceMapper.exMainImgUpload", exFileDto);
    }

    public int exCode() {
        return sqlSessionTemplate.selectOne("dao.ExperienceMapper.exCode");
    }

    public List<HostDto> hostChkList(int memberCode) {
        return sqlSessionTemplate.selectList("dao.ExperienceMapper.hostChkList", memberCode);
    }

    public int getReviewCnt(int exCode) {
        return sqlSessionTemplate.selectOne("dao.ExperienceMapper.reviewCnt", exCode);
    }

    public List<ExReviewListDto> getExReviewList(int startRow, int endRow, int exCode) {
        Map<String, Integer> hMap = new HashMap<>();
        hMap.put("startRow", startRow);
        hMap.put("endRow", endRow);
        //hMap.put("memberCode", memberCode);
        hMap.put("exCode", exCode);

        return sqlSessionTemplate.selectList("dao.ExperienceMapper.reviewList", hMap);
    }

    // ?????? // ??????????????? ????????? cnt
    public int reserveCodeCnt(int memberCode, int exCode) {
        Map<String, Integer> hMap = new HashMap<>();
        hMap.put("memberCode", memberCode);
        hMap.put("exCode", exCode);
        return sqlSessionTemplate.selectOne("dao.ExperienceMapper.exReserveCodeCnt", hMap);
    }

    public List<ExReserveDto> exReserveCode(int exCode, int memberCode) {
        Map<String, Integer> hMap = new HashMap<>();
        hMap.put("memberCode", memberCode);
        hMap.put("exCode", exCode);
        return sqlSessionTemplate.selectList("dao.ExperienceMapper.exReserveCode", hMap);
    }

    public int reviewChk(int exReserveCode) {
        return sqlSessionTemplate.selectOne("dao.ExperienceMapper.reviewChk", exReserveCode);
    }

    public int writeReview(ExReviewDto exReviewDto) {
        return sqlSessionTemplate.insert("dao.ExperienceMapper.writeReview", exReviewDto);
    }

    public ExReviewDto exReviewUpdate(int memberCode, int exReserveCode) {
        Map<String, Integer> hMap = new HashMap<>();
        hMap.put("exCode", memberCode);
        hMap.put("exReserveCode", exReserveCode);

        return sqlSessionTemplate.selectOne("dao.ExperienceMapper.exReviewUpdate", memberCode);
    }

    public int exReviewUpdateOk(ExReviewDto exReviewDto) {
        return sqlSessionTemplate.update("dao.ExperienceMapper.exReviewUpdateOk", exReviewDto);
    }

    public int exReviewDelete(int exReserveCode) {
        return sqlSessionTemplate.delete("dao.ExperienceMapper.exReviewDelete", exReserveCode);
    }

    // ???????????????
    public ExperienceDto exPage(int exCode) {
        return sqlSessionTemplate.selectOne("dao.ExperienceMapper.exPage", exCode);
    }

    public List<ExFileDto> exPageImgList(int exCode) {
        Map<String, Integer> hMap = new HashMap<>();
        hMap.put("exCode", exCode);

        return sqlSessionTemplate.selectList("dao.ExperienceMapper.exPageImgList", hMap);
    }

    public MemberDto exHostInfo(int exCode) {
        return sqlSessionTemplate.selectOne("dao.ExperienceMapper.exHostInfo", exCode);
    }

    public int exPayment(int exPeople, int exCode) {
        Map<String, Integer> hMap = new HashMap<>();
        hMap.put("exPeople", exPeople);
        hMap.put("exCode", exCode);
        return sqlSessionTemplate.selectOne("dao.ExperienceMapper.exPayment", hMap);
    }

    public int getPoint(String email) {
        return sqlSessionTemplate.selectOne("dao.ExperienceMapper.getPoint", email);
    }

    public int insertExReserve(ExReserveDto exReserveDto) {
        return sqlSessionTemplate.insert("dao.ExperienceMapper.insertExReserve", exReserveDto);
    }

    public int getExReserveCode(int exCode, int memberCode, Date exDate) {
        Map<String, Object> hMap = new HashMap<>();
        hMap.put("exCode", exCode);
        hMap.put("memberCode", memberCode);
        hMap.put("exDate", exDate);

        return sqlSessionTemplate.selectOne("dao.ExperienceMapper.getExReserveCode", hMap);
    }

    public int pointUpdate(int memberCode, int plusPoint) {
        Map<String, Integer> hMap = new HashMap<>();
        hMap.put("memberCode", memberCode);
        hMap.put("plusPoint", plusPoint);

        return sqlSessionTemplate.update("dao.ExperienceMapper.pointUpdate", hMap);
    }

    // ?????? ???????????? ?????? ??????
    public List<ExReserveDto> reserveList(int exCode, Date exDate) {
        Map<String, Object> hMap = new HashMap<>();
        hMap.put("exCode", exCode);
        hMap.put("exDate", exDate);

        return sqlSessionTemplate.selectList("dao.ExperienceMapper.reserveList", hMap);
    }

    // ????????? ?????? ????????? ??????
    public int getPeople(int exCode) {
        return sqlSessionTemplate.selectOne("dao.ExperienceMapper.getPeople", exCode);
    }

    // exRemainDto?????? exCode??? ?????? ??? ????????????!!!
    public List<ExRemainDto> getExRemain(int exCode) {
        return sqlSessionTemplate.selectList("dao.ExperienceMapper.getExRemain", exCode);
    }

    // ?????? ???????????? ?????? ?????? ????????????  ????????? ??????
    public int insertExRemain(Date exDate, int exPeople, int exCode) {
        Map<String, Object> hMap = new HashMap<>();
        hMap.put("exDate", exDate);
        hMap.put("exPeople", exPeople);
        hMap.put("exCode", exCode);

        return sqlSessionTemplate.insert("dao.ExperienceMapper.insertExRemain", hMap);
    }

    // ????????? ?????????

    public int message(int memberCode, String msgContent, Date msgDate, String msgCheck) {
        Map<String, Object> hMap = new HashMap<>();
        hMap.put("memberCode", memberCode);
        hMap.put("msgContent", msgContent);
        hMap.put("msgDate", msgDate);
        hMap.put("msgCheck", msgCheck);

        return sqlSessionTemplate.insert("dao.ExperienceMapper.message", hMap);
    }

    // ????????? ???????????? ?????? ?????? ????????????
    public MemberDto getMemberInfo(String email) {
        return sqlSessionTemplate.selectOne("dao.ExperienceMapper.getMemberInfo", email);
    }

    // ?????? ?????? ?????????
    public List<ExperienceMainDto> searchMainEx() {
        // ?????? ????????? ?????? ????????? ????????????
        List<ExperienceMainDto> exImgList = sqlSessionTemplate.selectList("dao.ExperienceMapper.searchMainEx");
        HomeAspect.logger.info(HomeAspect.logMsg + "exImgList: " + exImgList);

        // ?????? ????????? ???????????? ????????? ?????? ????????????
        for (ExperienceMainDto exImgDto : exImgList) {
            List<ExFileDto> exFileList = sqlSessionTemplate.selectList("dao.searchMapper.getExImg", exImgDto.getExCode());
            exImgDto.setExFileList(exFileList);
        }
        return exImgList;
    }

    // ?????? ?????? ?????????
    public List<GuestHouseMainDto> searchMain() {
        // ?????? ????????? ?????? ????????? ????????????
        List<GuestHouseMainDto> houseImgList = sqlSessionTemplate.selectList("dao.ExperienceMapper.searchMainGh");
        HomeAspect.logger.info(HomeAspect.logMsg + "houseImgList: " + houseImgList);

        // ?????? ????????? ???????????? ????????? ?????? ????????????
        for (GuestHouseMainDto ImgDto : houseImgList) {
            List<FileDto> FileList = sqlSessionTemplate.selectList("dao.searchMapper.getHouseImg", ImgDto.getHouseCode());
            ImgDto.setFileList(FileList);
        }
        return houseImgList;
    }

    public List<String> exDisableDates(Map<String, Object> map) {
        return sqlSessionTemplate.selectList("dao.ExperienceMapper.exDisableDates", map);
    }

}
