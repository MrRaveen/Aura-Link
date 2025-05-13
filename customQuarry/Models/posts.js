const express = require('express');
var DataTypes = require('sequelize/lib/data-types');
const sequelize = require('../dbConn');
class posts{
    getDataModel(){
        return sequelize.define("posts",{
            postid: {
                type: DataTypes.INTEGER,
                autoIncrement: true,
                primaryKey: true,
            },
            content_type: DataTypes.ENUM(["IMAGE","TEXT","VIDEO","VIDEO_IMAGES"]),
            date: DataTypes.DATE,
            description: DataTypes.STRING,
            time: DataTypes.TIME,
            title: DataTypes.STRING,
            user_id: {
                type: DataTypes.INTEGER,
                references: {
                    model: 'users', 
                    key: 'user_id'    
                }
            },
        },{timestamps: false});
    }
}
module.exports = posts;