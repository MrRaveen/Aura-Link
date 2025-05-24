const express = require('express');
var DataTypes = require('sequelize/lib/data-types');
const sequelize = require('../dbConn');
class post_reaction{
    getDataModel(){
        return sequelize.define('post_reaction',{
            reactionid: 
            {
            type: DataTypes.INTEGER,
            autoIncrement: true,
            primaryKey: true,
            },
            react_type: DataTypes.ENUM(["LAUGH","LIKE","LOVE","SAD"]),
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
module.exports = post_reaction;