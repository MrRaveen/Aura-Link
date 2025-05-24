const express = require('express');
var DataTypes = require('sequelize/lib/data-types');
const sequelize = require('../dbConn');
class follower_info{
    getDataModel(){
        return sequelize.define("follower_info",{
            followerid: {
                type: DataTypes.INTEGER,
                autoIncrement: true,
                primaryKey: true,
            },
            time: DataTypes.TIME,
            follower: {
                type: DataTypes.INTEGER,
                references: {
                    model: 'users', 
                    key: 'user_id'    
                }
            },
            user_id: {
                type: DataTypes.INTEGER,
                references: {
                    model: 'users', 
                    key: 'user_id'    
                }
            },
        },{timestamps: false,freezeTableName: true});
    }
}
module.exports = follower_info;