package cz.muni.fi.pa165.enums;


import org.springframework.util.StringUtils;

public enum LegalStatusEnum {
    PERSON,
    COMPANY;

    @Override
    public String toString() {
        return StringUtils.capitalize(this.name().toLowerCase());
    }
}
