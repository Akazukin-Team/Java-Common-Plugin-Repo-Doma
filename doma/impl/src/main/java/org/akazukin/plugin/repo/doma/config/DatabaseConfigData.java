package org.akazukin.plugin.repo.doma.config;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;
import org.akazukin.plugin.repo.doma.driver.IDriver;

@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public final class DatabaseConfigData implements IDatabaseConfigData {
    IDriver driver;

    String authority;

    String path;

    String username;

    String password;

    String args;

    @Override
    public void restoreDefaults() {
        this.driver = null;
        this.authority = null;
        this.path = null;
        this.username = null;
        this.password = null;
        this.args = null;
    }
}
