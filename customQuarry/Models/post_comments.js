const express = require('express');
var DataTypes = require('sequelize/lib/data-types');
const sequelize = require('../dbConn');
class post_comments{
    getDataModel(){
        return sequelize.define('post_comments',{
            commentid: 
            {
            type: DataTypes.INTEGER,
            autoIncrement: true,
            primaryKey: true,
            },
            comment: DataTypes.STRING,
            postid: 
             {
            type: DataTypes.INTEGER,
            references: {
                        model: 'posts', 
                        key: 'postid'    
                        }
             },
              user_id: {
                             type: DataTypes.INTEGER,
                             references: {
                                 model: 'users', 
                                 key: 'user_id'    
                             }
                         },
        },{freezeTableName: true,timestamps: false});
    }
}
module.exports = post_comments;