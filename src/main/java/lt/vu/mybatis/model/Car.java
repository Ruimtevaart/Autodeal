package lt.vu.mybatis.model;

public class Car {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column PUBLIC.CAR.ID
     *
     * @mbg.generated Sun Mar 03 13:45:02 EET 2019
     */
    private Integer id;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column PUBLIC.CAR.NAME
     *
     * @mbg.generated Sun Mar 03 13:45:02 EET 2019
     */
    private String name;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column PUBLIC.CAR.OWNER_ID
     *
     * @mbg.generated Sun Mar 03 13:45:02 EET 2019
     */
    private Integer carId;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column PUBLIC.CAR.ID
     *
     * @return the value of PUBLIC.CAR.ID
     *
     * @mbg.generated Sun Mar 03 13:45:02 EET 2019
     */
    public Integer getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column PUBLIC.CAR.ID
     *
     * @param id the value for PUBLIC.CAR.ID
     *
     * @mbg.generated Sun Mar 03 13:45:02 EET 2019
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column PUBLIC.CAR.NAME
     *
     * @return the value of PUBLIC.CAR.NAME
     *
     * @mbg.generated Sun Mar 03 13:45:02 EET 2019
     */
    public String getName() {
        return name;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column PUBLIC.CAR.NAME
     *
     * @param name the value for PUBLIC.CAR.NAME
     *
     * @mbg.generated Sun Mar 03 13:45:02 EET 2019
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column PUBLIC.CAR.OWNER_ID
     *
     * @return the value of PUBLIC.CAR.OWNER_ID
     *
     * @mbg.generated Sun Mar 03 13:45:02 EET 2019
     */
    public Integer getOwnerId() {
        return carId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column PUBLIC.CAR.OWNER_ID
     *
     * @param carId the value for PUBLIC.CAR.OWNER_ID
     *
     * @mbg.generated Sun Mar 03 13:45:02 EET 2019
     */
    public void setOwnerId(Integer carId) {
        this.carId = carId;
    }
}