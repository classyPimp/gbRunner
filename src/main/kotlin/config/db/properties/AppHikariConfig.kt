package config.db.properties

import com.zaxxer.hikari.HikariConfig

/**
 * Created by classypimp on 9/11/17.
 */
object AppHikariConfig {

    fun get(dBConnectionProperties: DbConnectionProperties) : HikariConfig {
        val config = HikariConfig()

        dBConnectionProperties.apply {
            config.jdbcUrl = url
            config.username = userName
            config.password = password
            config.driverClassName = driverClassName
            //TODO: set max poolsize from config file
        }

        return config
    }

}

