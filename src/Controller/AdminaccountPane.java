package Controller;

import Model.*;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.MenuBar;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import java.text.SimpleDateFormat;
import java.time.LocalDate;



public class AdminaccountPane extends AdminUIController {

    
    SecuritiesAccountDBManager db = new SecuritiesAccountDBManager();
    @FXML
    public void backtoMain1(ActionEvent event){
        //todo跟大组连接用不用写 
    }

    @FXML
    private MenuBar menuBar;

    @FXML
    private Text welcome;

    @FXML
    private StackPane pageressui;

    @FXML
    private StackPane pagecc;

    @FXML
    private StackPane pagechose;

    @FXML
    private StackPane pagefr;

    @FXML
    private StackPane pagers;

    @FXML
    private StackPane pagecprs;

    @FXML
    private StackPane pagems;

    @FXML
    private StackPane pagepsrs;

    //个人注册
    @FXML
    private DatePicker date;

    @FXML
    private TextField psname;

    @FXML
    private TextField psid;

    @FXML
    private Button pscancelbtn;

    @FXML
    private Label message;

    @FXML
    private TextField prof;

    @FXML
    private ToggleGroup group1;

    @FXML
    private Button psokbutton;

    @FXML
    private TextField psaddr;

    @FXML
    private TextField pstel;

    @FXML
    private TextField psjob;

    @FXML
    private TextField rppsid;

    @FXML
    private RadioButton man;

    @FXML
    private TextField psacc;

    @FXML
    private TextField diplome;

    @FXML
    private RadioButton women;

    private Main application;

    private ClassAccount account;
    
    
    public void setApp(Main application){
        this.application = application;
    }

    private boolean checktextField(TextField str){
        return(str.getText() != null && !str.getText().isEmpty());
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // 
    }

    public void initTime(){
        LocalDate date1=LocalDate.now();
        date.setValue(date1);
    }

    public boolean personalcheck(){
        boolean check = false; 
        if(!checktextField(psname)){
            message.setText("请输入你的名字");
            message.setVisible(true);
            return check;
        }

        if(!checktextField(psid)){
            message.setText("请输入你的身份证号");
            message.setVisible(true);
            return check;
        }
        if(psid.getText().length()!=18){
            message.setText("请输入正确的身份证号");
            message.setVisible(true);
            return check;
        }
        if(!checktextField(prof)){
            message.setVisible(true);
            message.setText("请输入你的职业");
            return check;
        }
        if(!checktextField(psaddr)){
            message.setVisible(true);
            message.setText("请输入你的地址");
            return check;
        }
        if(!checktextField(pstel)){
            message.setVisible(true);
            message.setText("请输入你的电话");
            return check;
        }
        if(!checktextField(psjob)){
            message.setVisible(true);
            message.setText("请输入你的工作");
            return check;
        }
        if(!checktextField(diplome)){
            message.setVisible(true);
            message.setText("请输入你的学历");
            return check;
        }
        if(!man.isSelected() && !women.isSelected()){
            message.setVisible(true);
            message.setText("请选择你的性别");
            return check;
        }
        return true;
    }

    @FXML
    public void personalregister(ActionEvent event) {
        
        if(!this.personalcheck()){
        return;
        }//todo还需验证账户是否存在以及删除等情况

        PersonalAccount temp = new PersonalAccount();
        if(db.getPersonalAccount(psid.getText(), temp)){
            message.setText("您已经注册过证券账户！");
            message.setVisible(true);
            return;
        }

        boolean sex;
        if(man.isSelected()){
            sex=true;
        }else{
            sex=false;
        }

        if(checktextField(rppsid)){
            PersonalAccount account1= new PersonalAccount(java.sql.Date.valueOf(date.getValue()), psname.getText(), sex, psid.getText(), psaddr.getText(), prof.getText(), diplome.getText(), psjob.getText(), pstel.getText(), rppsid.getText());
            db.newPersonalAccount(account1, 0);
            db.getPersonalAccount(psid.getText(), account1);
            this.goToMessage("恭喜注册成功",String.valueOf(account1.getSecuritiesId()));
        }else{
            PersonalAccount account1= new PersonalAccount(java.sql.Date.valueOf(date.getValue()), psname.getText(), sex, psid.getText(), psaddr.getText(), prof.getText(), diplome.getText(), psjob.getText(), pstel.getText());
            db.newPersonalAccount(account1, 1);
            db.getPersonalAccount(psid.getText(), account1);
            this.goToMessage("恭喜注册成功", String.valueOf(account1.getSecuritiesId()));
        }
    }


    @FXML
    public void cancelBtn(ActionEvent event) {
        pagepsrs.setVisible(false);
        pagechose.setVisible(true);
    }

    //公司注册
    @FXML
    private TextField cptradername;

    @FXML
    private TextField cptradertel;

    @FXML
    private Button cpregisterBtn;

    @FXML
    private TextField cpname;

    @FXML
    private TextField cpid;

    @FXML
    private Button cpbackBtn;

    @FXML
    private TextField cptel;

    @FXML
    private TextField cpaddr;

    @FXML
    private TextField cptraderid;

    @FXML
    private TextField cptraderaddr;

    @FXML
    private Label message1;

    @FXML
    private TextField cplicence;

    @FXML
    private TextField cprpid;

    public boolean companycheck(){
        if(!checktextField(cpid)){
            message1.setText("请输入法人注册登记号");
            message1.setVisible(true);
            return  false;
        }

        if(!checktextField(cplicence)){
            message1.setText("请输入营业执照编号");
            message1.setVisible(true);
            return false;
        }

        if(!checktextField(cprpid)){
            message1.setText("请输入法人身份证号");
            message1.setVisible(true);
            return false;
        }
        
        if(!checktextField(cptradername)){
            message1.setText("请输入授权人姓名");
            message1.setVisible(true);
            return false;
        }
   
        if(!checktextField(cptradertel)){
            message1.setText("请输入授权人电话");
            message1.setVisible(true);
            return false;
        }

        if(!checktextField(cpname)){
            message1.setText("请输入法人姓名");
            message1.setVisible(true);
            return false;
        }


        if(cpid.getText().length()!=9){
            ccms.setText("请输入正确的法人注册登记号");
            ccms.setVisible(true);
            return false;
        }

        if(!checktextField(cptel)){
            message1.setText("请输入法人联系电话");
            message1.setVisible(true);
            return false;
        }

        if(!checktextField(cpaddr)){
            message1.setText("请输入法人联系地址");
            message1.setVisible(true);
            return false;
        }

        if(!checktextField(cptraderid)){
            message1.setText("请输入授权人有效身份证号");
            message1.setVisible(true);
            return false;
        }

        if(cptraderid.getText().length()!=18){
            message1.setText("请输入正确的授权人有效身份证号");
            message1.setVisible(true);
            return false;
        }

        if(!checktextField(cptraderaddr)){
            message1.setText("请输入授权人地址");
            message1.setVisible(true);
            return false;
        }

         if(cprpid.getText().length()!=18){
            message1.setText("请输入法人身份证号");
            message1.setVisible(true);
            return false;
        }
        return true;

    }

    @FXML
    void companyregister(ActionEvent event) {

        if(!companycheck()){
            return;
        }
        CorporateAccount temp = new CorporateAccount();
        if(db.getCorporateAccount(cpid.getText(), temp)){
            message1.setText("该法人注册账号已经注册过证券账户！");
            message1.setVisible(true);
            return ;
        }
        //todo还需验证账户存在等问题
        temp = new CorporateAccount(cpid.getText(),cplicence.getText(), cprpid.getText(),cpname.getText(), cptel.getText(),  cpaddr.getText(), cptradername.getText(), cptraderid.getText(), cptradertel.getText(), cptraderaddr.getText());
        db.newCorporateAccount(temp);
        db.getCorporateAccount(cpid.getText(), temp);
        this.goToMessage("恭喜注册成功", String.valueOf(temp.getSecuritiesId()));
    }

    @FXML
    void goback(ActionEvent event) {
        pagecprs.setVisible(false);
        pagechose.setVisible(true);
    }

    //选择注册的方向
    @FXML
    private Button cprs;

    @FXML
    private Button psrs;

    @FXML
    void jumppsrs(ActionEvent event) {
        this.initTime();
        pagepsrs.setVisible(true);
        pagers.setVisible(false);
        psressuieBtn.setVisible(false);
        psokbutton.setVisible(true);

    }

    @FXML
    void jumpcprs(ActionEvent event) {
        pagecprs.setVisible(true);
        pagers.setVisible(false);
        cpressuieBtn.setVisible(false);
        cpregisterBtn.setVisible(true);
    }


    //选择功能
    @FXML
    private Button rsBtn;

    @FXML
    private Button frBtn;

    @FXML
    private Button ccBTn;

    @FXML
    private Button ressuieBtn;

    @FXML
    void jumprs(ActionEvent event) {
        pagechose.setVisible(false);
        pagers.setVisible(true);
    }

    @FXML
    void jumptofr(ActionEvent event) {
        pagechose.setVisible(false);
        pagefr.setVisible(true);
    }

    @FXML
    void jumptocc(ActionEvent event) {
        pagechose.setVisible(false);
        pagecc.setVisible(true);
    }

    @FXML
    void jumptoressuie(ActionEvent event) {
        pagechose.setVisible(false);
        pageressui.setVisible(true);
    }

    //注销
     @FXML
    private TextField ccaccnb;

    @FXML
    private Button ccbackBtn;

    @FXML
    private Button ccokBtn;

    @FXML
    private TextField ccidNb;

    @FXML
    private Label ccms;



    @FXML
    public void cancelok(ActionEvent event) {
        if(!checktextField(ccaccnb)){
            ccms.setText("请输入你的账户");
            ccms.setVisible(true);
            return;
        }

        if(!checktextField(ccidNb)){
            ccms.setText("请输入你的身份证号或法人注册号");
            ccms.setVisible(true);
            return;
        }
        if(ccidNb.getText().length()!=18&&ccidNb.getText().length()!=9){
            ccms.setText("请输入正确的身份证号或法人注册号");
            ccms.setVisible(true);
            return ;
        }

        int flag = 0;
        PersonalAccount personal_temp = new PersonalAccount();
        CorporateAccount corporate_temp = new CorporateAccount();
        if(!db.getCorporateAccount(ccidNb.getText(), corporate_temp)){
            flag = 1; // 法人账户不存在
        }else{
            if(corporate_temp.getState() == 1){
                ccms.setText("该账户目前已被冻结，请选择补办");
                ccms.setVisible(true);
                return;
            }
            boolean delete_result = db.deleteCorporateAccount(ccidNb.getText());
            if(delete_result == false){
                ccms.setText("删除失败！");
                ccms.setVisible(true);
            }else{
                this.goToMessage("恭喜删除成功", String.valueOf(corporate_temp.getSecuritiesId()));
            }
            return ;
        }
        if(!db.getPersonalAccount(ccidNb.getText(), personal_temp)){
            flag = 2; // 个人账户不存在
        }else{
            if(personal_temp.getState() == 1){
                ccms.setText("该账户目前已被冻结，请选择补办");
                ccms.setVisible(true);
                return;
            }
            boolean delete_result = db.deletePersonalAccount(ccidNb.getText());
            if(delete_result == false){
                ccms.setText("删除失败！");
                ccms.setVisible(true);
            }else{
                this.goToMessage("恭喜删除成功", String.valueOf(personal_temp.getSecuritiesId()));
            }
            return ;
        }


        if(flag == 1 || flag == 2){
            ccms.setText("该账号不存在");
            ccms.setVisible(true);
            return;
        }
        //todo还需验证账户存在等问题

        //this.goToMessage("注销成功",account.getSecurities_id());
    }
   
   
    //冻结
     @FXML
    private Button frbackBtn;

    @FXML
    private Label frms;

    @FXML
    private Button frokBtn;

    @FXML
    private TextField fridNb;

    @FXML
    void fronzen(ActionEvent event) {
         if(!checktextField(fridNb)){
            frms.setText("请输入身份证号");
            frms.setVisible(true);
            return;
        }
        if(fridNb.getText().length()!=18&&fridNb.getText().length()!=9){
            frms.setText("请输入正确的身份证号或法人注册号");
            frms.setVisible(true);
            return ;
        }
        int flag = 0;
        PersonalAccount personal_temp = new PersonalAccount();
        CorporateAccount corporate_temp = new CorporateAccount();
        if(!db.getCorporateAccount(fridNb.getText(), corporate_temp)){
            flag = 1; // 法人账户不存在
        }else{
            db.modifyCorporateState(fridNb.getText(), 1);
            this.goToMessage("冻结成功",String.valueOf(corporate_temp.getSecuritiesId()));
            return ;
        }
        if(!db.getPersonalAccount(fridNb.getText(), personal_temp)){
            flag = 2; // 个人账户不存在
        }else{
            db.modifyPersonalState(fridNb.getText(), 1);
            this.goToMessage("冻结成功",String.valueOf(personal_temp.getSecuritiesId()));
            return ;
        }


        if(flag == 1 || flag == 2){
            this.goToMessage("该账户不存在",fridNb.getText());
            return;
        }
        //todo


    }

    @FXML
    void goBack(ActionEvent event) {
        pagechose.setVisible(true);
        pagefr.setVisible(false);
        pagecc.setVisible(false);
    }

    //信息弹窗
    @FXML // fx:id="message"
    private Label msms; // Value injected by FXMLLoader

    @FXML // fx:id="okBtn"
    private Button msokBtn; // Value injected by FXMLLoader

    @FXML // fx:id="account"
    private Label msacc; // Value injected by FXMLLoader

    public void setText1(String message5,String account1){
        this.msms.setText(message5);
        this.msacc.setText("账号: "+account1);
    }

    @FXML
    public void backtoMain(ActionEvent event) {
        pagechose.setVisible(true);
        pagems.setVisible(false);
    }

    public void goToMessage(String str,String str1){
        setText1(str,str1);
        pagems.setVisible(true);
        pagefr.setVisible(false);
        pagecc.setVisible(false);
        pagepsrs.setVisible(false);
        pagecprs.setVisible(false);
    }
    //补办

    @FXML
    private Button cprs1;

    @FXML
    private Button psrs1;

    @FXML
    private Button psressuieBtn;

    @FXML
    private Button cpressuieBtn;

    @FXML
    void jumppsrs1(ActionEvent event) {
        this.initTime();
        pagepsrs.setVisible(true);
        pageressui.setVisible(false);
        psressuieBtn.setVisible(true);
        psokbutton.setVisible(false);

    }

    @FXML
    void jumpcprs1(ActionEvent event) {
        pagecprs.setVisible(true);
        pageressui.setVisible(false);
        cpressuieBtn.setVisible(true);
        cpregisterBtn.setVisible(false);
    }

    @FXML
    void companyressiue(ActionEvent event){
        if(!companycheck()){
            return;
        }
        CorporateAccount account = new CorporateAccount();
        account = new CorporateAccount(cpid.getText(),cplicence.getText(), cprpid.getText(),cpname.getText(), cptel.getText(),  cpaddr.getText(), cptradername.getText(), cptraderid.getText(), cptradertel.getText(), cptraderaddr.getText());
        CorporateAccount old_account = new CorporateAccount();
        if(!db.getCorporateAccount(account.getRegisterNo(), old_account)){
            message1.setText("您还没有注册过证券账户！");
            message1.setVisible(true);
            return;
        }else{
            if(old_account.getState() == 0){
                message1.setText("您的账户目前是正常状态，无须补办！");
                message1.setVisible(true);
                return;
            }else {
                db.deleteCorporateAccount(old_account.getRegisterNo());
                db.newCorporateAccount(account);
                CorporateAccount temp = new CorporateAccount();
                db.getCorporateAccount(account.getRegisterNo(), temp);
                db.modifySecuritiesFunds(old_account.getSecuritiesId(), temp.getSecuritiesId());
                this.goToMessage("恭喜补办成功",String.valueOf(temp.getSecuritiesId()));
            }
        }
        //todo
    }

    @FXML
    void psressuie(ActionEvent event){
        if(!personalcheck()){
            return;
        }

        boolean sex;
        if(man.isSelected()){
            sex=true;
        }else{
            sex=false;
        }

        int flag = 0;
        PersonalAccount account = new PersonalAccount();
        if(checktextField(rppsid)){
            account= new PersonalAccount(java.sql.Date.valueOf(date.getValue()), psname.getText(), sex, psid.getText(), psaddr.getText(), prof.getText(), diplome.getText(), psjob.getText(), pstel.getText(), rppsid.getText());
            flag = 0;
        }else{
            account= new PersonalAccount(java.sql.Date.valueOf(date.getValue()), psname.getText(), sex, psid.getText(), psaddr.getText(), prof.getText(), diplome.getText(), psjob.getText(), pstel.getText());
            flag = 1;
        }
        PersonalAccount old_account = new PersonalAccount();
        if(!db.getPersonalAccount(account.getIdNo(), old_account)){
            message1.setText("您还没有注册过证券账户！");
            message1.setVisible(true);
            return;
        }else{
            if(old_account.getState() == 0){
                message1.setText("您的账户目前是正常状态，无须补办！");
                message1.setVisible(true);
                return;
            }else {
                db.deletePersonalAccount(old_account.getIdNo());
                db.newPersonalAccount(account, flag);
                PersonalAccount temp = new PersonalAccount();
                db.getPersonalAccount(account.getIdNo(), temp);
                db.modifySecuritiesFunds(old_account.getSecuritiesId(), temp.getSecuritiesId());
                this.goToMessage("恭喜补办成功",String.valueOf(temp.getSecuritiesId()));
            }
        }

            //todo
    }


  

}
