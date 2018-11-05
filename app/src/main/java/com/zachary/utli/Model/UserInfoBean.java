package com.zachary.utli.Model;

import java.io.Serializable;
import java.util.List;

public class UserInfoBean implements Serializable {

    /*
        response:
    {
      "code": "1",
      "msg": "操作成功",
      "data": {
        "empId": "0",
        "empName": "管理员",
        "projectList": [
          {
            "guid": "130719ac-4ed0-4fa2-8db4-af0d60b7e0bd",
            "projectCode": "abc",
            "projectName": "asda",
            "areaCode": "123"
          },
          {
            "guid": "189df75f-82d9-4eff-8817-c18e42793912",
            "projectCode": "poiu",
            "projectName": "nnk",
            "areaCode": "123"
          },
          {
            "guid": "c922e5f2-ec5b-458b-b185-a757983ed03a",
            "projectCode": "zhaokaiyue",
            "projectName": "asdasda",
            "areaCode": "12"
          }
        ],
        "company_NAME": "省公司",
        "head_IMAGE": null,
        "company_CODE": "A10"
      }
    }
         */
    private String empId;
    private String empName;
    private String company_NAME;
    private String head_IMAGE;
    private String company_CODE;
    private String custom01;

    List<project> projectList ;

    public class project implements Serializable{
        private String guid;
        private String projectCode;
        private String projectName;
        private String areaCode;

        public void setGuid(String guid) {

            this.guid = guid;
        }

        public void setProjectCode(String projectCode) {
            this.projectCode = projectCode;
        }

        public void setProjectName(String projectName) {
            this.projectName = projectName;
        }

        public void setAreaCode(String areaCode) {
            this.areaCode = areaCode;
        }

        public String getGuid() {
            return guid;
        }

        public String getProjectCode() {
            return projectCode;
        }

        public String getProjectName() {
            return projectName;
        }

        public String getAreaCode() {
            return areaCode;
        }
    }

    public void setEmpId(String empId) {
        this.empId = empId;
    }

    public void setEmpName(String empName) {
        this.empName = empName;
    }

    public void setCompany_NAME(String company_NAME) {
        this.company_NAME = company_NAME;
    }

    public void setHead_IMAGE(String head_IMAGE) {
        this.head_IMAGE = head_IMAGE;
    }

    public void setCompany_CODE(String company_CODE) {
        this.company_CODE = company_CODE;
    }

    public void setProjectList(List<project> projectList) {
        this.projectList = projectList;
    }

    public String getEmpId() {
        return empId;
    }

    public String getEmpName() {
        return empName;
    }

    public String getCompany_NAME() {
        return company_NAME;
    }

    public String getHead_IMAGE() {
        return head_IMAGE;
    }

    public String getCompany_CODE() {
        return company_CODE;
    }

    public List<project> getProjectList() {
        return projectList;
    }

    public String getCustom01() {
        return custom01;
    }

    public void setCustom01(String custom01) {
        this.custom01 = custom01;
    }

}
