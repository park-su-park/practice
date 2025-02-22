package park_su_park.backend.message;

public interface USERMESSAGE {

    public static final String CREATE_SUCCESS = "유저 생성 성공";
    public static final String READ_SUCCESS = "유저 조회 성공";
    public static final String UPDATE_SUCCESS = "유저 수정 성공";
    public static final String DELETE_SUCCESS = "유저 삭제 성공";

    public static final String USED_Email = "이미 존재하는 email입니다";

    public static final String USED_USERNAME = "이미 존재하는 username입니다";

    public static final String USED_USERNAME_AND_EMAIL = "이미 존재하는 username과 email입니다";
    public static final String NOT_EXIST = "해당 id를 가진 user를 조회할 수 없습니다.";


}
