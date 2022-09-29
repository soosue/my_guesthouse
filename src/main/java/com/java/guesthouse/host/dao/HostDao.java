package com.java.guesthouse.host.dao;

import java.util.List;

import com.java.guesthouse.experience.dto.ExperienceDto;
import com.java.guesthouse.file.dto.FileDto;
import com.java.guesthouse.guestReserve.dto.GuestReserveDto;
import com.java.guesthouse.host.dto.ExReservationListDto;
import com.java.guesthouse.host.dto.HostDto;
import com.java.guesthouse.host.dto.ReservationListDto;
import com.java.guesthouse.host.dto.SearchDateList;
import com.java.guesthouse.host.dto.SearchDateListCount;
import com.java.guesthouse.member.dto.MemberDto;

public interface HostDao {

	public int subImgUpload(FileDto fileDto);

	public int hostRegister(HostDto hostDto);

	public int memberProfileImg(MemberDto memberDto);

	public int memberCode(String email);

	public int mainImgUpload(FileDto fileDto);

	public int houseCode();

	public List<HostDto> houseList(int memberCode, int startRow, int endRow);

	public int hostCancel(int houseCode);

	public List<String> houseNameList(int memberCode);

	public int getHouseCode(String houseName);

	public List<ReservationListDto> reserveViewList(int houseCode, int startRow, int endRow);

	public int getHouseCount(String email);

	public int getReserveCount(int houseCode);

	public int getExCount(int memberCode);

	public List<ExperienceDto> experienceList(int memberCode, int startRow, int endRow);

	public List<GuestReserveDto> getSales(int memberCode);

	public SearchDateListCount getSearchDateCount(int memberCode, String startDate, String endDate);

	public List<SearchDateList> searchDateList(int memberCode, String startDate, String endDate, int startRow,
			int endRow);

	public MemberDto selectMemberDto(int memberCode);

	public List<HostDto> ahouseList(int memberCode);

	public int exCancel(int exCode);

	public List<String> exNameList(int memberCode);

	public int getExCode(String exName);

	public int getExReserveCount(int exCode);

	public List<ExReservationListDto> exReserveViewList(int exCode, int startRow, int endRow);

	public String getLocal(String localName);

	public int houseNameCheck(String houseName);

}
