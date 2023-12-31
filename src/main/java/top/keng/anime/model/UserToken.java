package top.keng.anime.model;

import java.io.Serializable;
import java.util.Date;

public class UserToken implements Serializable {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column user_token.user_id
     *
     * @mbg.generated Fri May 19 10:36:22 CST 2023
     */
    private Long userId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column user_token.token
     *
     * @mbg.generated Fri May 19 10:36:22 CST 2023
     */
    private String token;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column user_token.insert_time
     *
     * @mbg.generated Fri May 19 10:36:22 CST 2023
     */
    private Date insertTime;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column user_token.expire_time
     *
     * @mbg.generated Fri May 19 10:36:22 CST 2023
     */
    private Date expireTime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table user_token
     *
     * @mbg.generated Fri May 19 10:36:22 CST 2023
     */
    private static final long serialVersionUID = 1L;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column user_token.user_id
     *
     * @return the value of user_token.user_id
     *
     * @mbg.generated Fri May 19 10:36:22 CST 2023
     */
    public Long getUserId() {
        return userId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table user_token
     *
     * @mbg.generated Fri May 19 10:36:22 CST 2023
     */
    public UserToken withUserId(Long userId) {
        this.setUserId(userId);
        return this;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column user_token.user_id
     *
     * @param userId the value for user_token.user_id
     *
     * @mbg.generated Fri May 19 10:36:22 CST 2023
     */
    public void setUserId(Long userId) {
        this.userId = userId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column user_token.token
     *
     * @return the value of user_token.token
     *
     * @mbg.generated Fri May 19 10:36:22 CST 2023
     */
    public String getToken() {
        return token;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table user_token
     *
     * @mbg.generated Fri May 19 10:36:22 CST 2023
     */
    public UserToken withToken(String token) {
        this.setToken(token);
        return this;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column user_token.token
     *
     * @param token the value for user_token.token
     *
     * @mbg.generated Fri May 19 10:36:22 CST 2023
     */
    public void setToken(String token) {
        this.token = token == null ? null : token.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column user_token.insert_time
     *
     * @return the value of user_token.insert_time
     *
     * @mbg.generated Fri May 19 10:36:22 CST 2023
     */
    public Date getInsertTime() {
        return insertTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table user_token
     *
     * @mbg.generated Fri May 19 10:36:22 CST 2023
     */
    public UserToken withInsertTime(Date insertTime) {
        this.setInsertTime(insertTime);
        return this;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column user_token.insert_time
     *
     * @param insertTime the value for user_token.insert_time
     *
     * @mbg.generated Fri May 19 10:36:22 CST 2023
     */
    public void setInsertTime(Date insertTime) {
        this.insertTime = insertTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column user_token.expire_time
     *
     * @return the value of user_token.expire_time
     *
     * @mbg.generated Fri May 19 10:36:22 CST 2023
     */
    public Date getExpireTime() {
        return expireTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table user_token
     *
     * @mbg.generated Fri May 19 10:36:22 CST 2023
     */
    public UserToken withExpireTime(Date expireTime) {
        this.setExpireTime(expireTime);
        return this;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column user_token.expire_time
     *
     * @param expireTime the value for user_token.expire_time
     *
     * @mbg.generated Fri May 19 10:36:22 CST 2023
     */
    public void setExpireTime(Date expireTime) {
        this.expireTime = expireTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table user_token
     *
     * @mbg.generated Fri May 19 10:36:22 CST 2023
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", userId=").append(userId);
        sb.append(", token=").append(token);
        sb.append(", insertTime=").append(insertTime);
        sb.append(", expireTime=").append(expireTime);
        sb.append("]");
        return sb.toString();
    }
}