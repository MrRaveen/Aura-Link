class imageAdd{
    constructor(imageFile){
        this.imageFile = imageFile;
    }
    handleImgInsert(){
    const file = this.imageFile.current.files[0];
    console.log('img insert : ' + file.name);//FIXME: test
    
}
}
export default imageAdd;