function drop {
    echo "dropping db if exists..."
    mysql -uroot -proot -e "drop database if exists autostoredb"
    echo "-- done!"
}

function create {
    echo "creating db..."
    mysql -uroot -proot -e "create database autostoredb"
    echo "-- done!"
}

drop
echo ""
create