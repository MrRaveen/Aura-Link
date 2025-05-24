const express = require('express');
var DataTypes = require('sequelize/lib/data-types');
const sequelize = require('../dbConn');
class user{
    getDataModel(){
        return sequelize.define("users",{
            user_id: {
                type: DataTypes.INTEGER,
                autoIncrement: true,
                primaryKey: true,
            },
            created_at: DataTypes.TIME,
            email: DataTypes.STRING,
            password_hash: DataTypes.STRING,
            username: DataTypes.INTEGER 
        },{
            timestamps: false
        });
    }
}
module.exports = user;