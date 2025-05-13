const Sequelize = require('sequelize');
 const sequelize  = new Sequelize(
            'travel_project',
            'root',
            'raveen007',
            {
                host: 'localhost',
                dialect: 'mysql'
            }
        );
module.exports = sequelize;