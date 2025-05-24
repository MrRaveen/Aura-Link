const express = require('express');
var DataTypes = require('sequelize/lib/data-types');
const sequelize = require('../dbConn');
class userProfile{
    getDataModel(){
        return sequelize.define('user_profiles', {
            id: {
                type: DataTypes.INTEGER,
                autoIncrement: true,
                primaryKey: true,
            },
            job: DataTypes.STRING,
            address: DataTypes.STRING,
            age: DataTypes.INTEGER,
            bio: DataTypes.STRING,
            birth_date: DataTypes.DATE(6),
            first_name: DataTypes.STRING,
            joined_at: DataTypes.TIME,
            last_name: DataTypes.STRING,
            lat_location: DataTypes.STRING,
            lon_location: DataTypes.STRING,
            mobile: DataTypes.INTEGER,
            profile_pic_url: DataTypes.STRING,
            user_id: {
                type: DataTypes.INTEGER,
                references: {
                    model: 'users', 
                    key: 'user_id'    
                }
            }
        }, {
            timestamps: false
        });
    }
}
module.exports = userProfile;