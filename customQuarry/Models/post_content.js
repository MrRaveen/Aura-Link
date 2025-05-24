const express = require('express');
var DataTypes = require('sequelize/lib/data-types');
const sequelize = require('../dbConn');
class post_content{
    getDataModel(){
        return sequelize.define('post_content',{
            contentid: 
            {
            type: DataTypes.INTEGER,
            autoIncrement: true,
            primaryKey: true,
            },
            media_name: DataTypes.STRING,
            meta_data: DataTypes.STRING,
            type: DataTypes.STRING,
            postid: 
             {
            type: DataTypes.INTEGER,
            references: {
                        model: 'posts', 
                        key: 'postid'    
                        }
             },
        },{freezeTableName: true,timestamps: false});
    }
}
module.exports = post_content;