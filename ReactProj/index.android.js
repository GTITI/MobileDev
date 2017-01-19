/**
 * Sample React Native App
 * https://github.com/facebook/react-native
 * @flow
 */

import React, {Component} from 'react';
import {
    AppRegistry,
    Alert,
    StyleSheet,
    Text,
    View,
    Navigator,
    ListView,
    TextInput,
    StatusBar,
    TouchableHighLight,
    TouchableNativeFeedback,
    TouchableOpacity,
    AsyncStorage
} from 'react-native';

import Communications from 'react-native-communications';
import Button from 'react-native-button';

var artworks = []


class Artwork extends Component {
  constructor(props) {
    super(props);
    this.state = {name: '',desc : ''};


  }
}

class AwesomeProject extends Component {
  constructor(props) {
    super(props);

    if (artworks.length == 0) {
      this._getPersistedData();
    }

    this.state = {
      name : '' ,
      desc: '',

      dataSource: new ListView.DataSource({
            rowHasChanged: (row1, row2) => row1 !== row2,
    }),
    loaded: false,
  };

  }

  componentDidMount(){
    this.setState({
      dataSource: this.state.dataSource.cloneWithRows(artworks),
      loaded: true,
    });
  }

  _persistData() {
    return AsyncStorage.setItem('key1', JSON.stringify(artworks))
            .then(json => console.log('success at persist save'))
  .catch(error => console.log('error at persist save'));
  }

  _getPersistedData() {
    return AsyncStorage.getItem('key1')
            .then(req => JSON.parse(req))
  .then(json => {

      console.log(json)


    for (var i = 0; i < json.length; i++) {
      artworks.push({
        "name": json[i].name,
        "desc": json[i].desc,

      });


      this.setState({
        dataSource: this.state.dataSource.cloneWithRows(artworks),
        loaded: true,
      });
    }
  })
  .catch(error => console.log('error at reading!'));
  }

  _addBtn(){
    if (this.state.name !== '' && this.state.desc != '') {
      artworks.push({"name": this.state.name, "desc": this.state.desc});
      Alert.alert("Done", "Artwork added!");
      this.setState({
        dataSource: this.state.dataSource.cloneWithRows(artworks),
        loaded: true,
      });
      this._persistData();
    } else {
      Alert.alert("Warning", "Some input(s) are empty!");
    }
  }


  _emailBtn() {
    var artworksString = artworks.map(function(item) {
      return "\nName: " + item['name'] + "\nDesc: " + item['desc'] + "\n";
    });

    Communications.email(["sadismek@gmail.com"],"","","Sent from react-native",artworksString.toString());
  }


  _navigate(artwork){
    this.props.navigator.push({
      name: 'EditDetails',

      passProps: {
        artwork : artwork
      }
    })

  }

  renderArtworks(artworks) {
    return (
        <TouchableOpacity
    onPress={ () => this._navigate(artworks)}>
  <View
    style={styles.viewDetails}>
  <Text>{artworks.name}</Text>
    <Text>{artworks.desc}</Text>


    </View>
    </TouchableOpacity>
  );
  }



  render(){
    return (

        <View  style={{backgroundColor: 'transparent'}}>

  <Text style={styles.header}> Welcome to Artwork Gallery</Text>

    <TextInput
    style= {styles.input}
    onChangeText={(text) => this.setState({name : text})}
    placeholder="name"
    value = {this.state.name}/>

  <TextInput
    style={styles.input}
    onChangeText={(text) => this.setState({desc : text})}
    placeholder="desc"
    value = {this.state.desc}/>




  <Button
    containerStyle={{padding:8, height:50, overflow:'hidden', borderRadius:6, backgroundColor: '#669999', marginBottom: 4}}
    style={{fontSize: 20, color: 'white'}}
    styleDisabled={{color: '#1565C0'}}
    onPress={() => this._addBtn()}>Add</Button>




    <Button
    containerStyle={{padding:8, height:50, overflow:'hidden', borderRadius:6, backgroundColor: '#527a7a'}}
    style={{fontSize: 20, color: 'white'}}
    styleDisabled={{color: '#1565C0'}}
    onPress={() => this._emailBtn()}>
    Send email
    </Button>

    <ListView
    dataSource={this.state.dataSource}
    renderRow={this.renderArtworks.bind(this)}
    style={styles.listView}/>

  </View>

  )
  }
}


//edit=>
class EditDetails extends React.Component{

  constructor(props){
    super(props);
    this.state = {
      name : this.props.artwork.name ,
      desc: this.props.artwork.desc,

    }

  }

  _persistData() {
    return AsyncStorage.setItem('key1', JSON.stringify(artworks))
            .then(json => console.log('success at persist save'))
  .catch(error => console.log('error at persist save'));
  }


  /**
   * when pressing the Save button
   */
  _handlePress() {
    if (this.state.name !== '' && this.state.desc != '') {
      this.props.artwork.name = this.state.name;
      this.props.artwork.desc = this.state.desc;

      Alert.alert("Saved");

      this._persistData();
      this.props.navigator.pop();


    } else {
      Alert.alert("Warning", "One or more fields are empty!");
    }
  }

  _handlePressDelete() {
    var index = artworks.indexOf(this.props.artwork);

    if (index > -1) {
      artworks.splice(index, 1);
      Alert.alert("Done", "Deleted.");

      this._persistData();

      this.props.navigator.push({
        name: 'AwesomeProject',
      })
    }
    else {
      Alert.alert("Warning", "Artwork not found");
    }
  }

  render(){
    return(
        <View style={{backgroundColor: 'white'}}>
  <Text style={styles.header}>Edit</Text>

    <TextInput
    style= {styles.input}
    onChangeText={(text) => this.setState({name : text})}
    placeholder="name"
    value = {this.state.name}
  />
  <TextInput
    style={styles.input}
    onChangeText={(text) => this.setState({desc : text})}
    placeholder="desc"
    value = {this.state.desc}
  />

  />

  <Button
    containerStyle={{padding:10, height:45, overflow:'hidden', borderRadius:4, backgroundColor: 'lightgrey', marginBottom: 4}}
    style={{fontSize: 20, color: 'black'}}
    styleDisabled={{color: 'red'}}
    onPress={ () => this._handlePress() }>
    Save and return
  </Button>

    <Button
    containerStyle={{
      padding: 10,
          height: 45,
          overflow: 'hidden',
          borderRadius: 4,
          backgroundColor: 'red',
          marginBottom: 4
    }}
    style={{fontSize: 20, color: 'white'}}
    styleDisabled={{color: 'red'}}
    onPress={ () => this._handlePressDelete() }>
    Delete
    </Button>
    </View>
  )
  }

}


var App = React.createClass({

  renderScene(route, navigator) {
    if(route.name == 'AwesomeProject') {
      return <AwesomeProject navigator={navigator} {...route.passProps}  />
    }
    if(route.name == 'EditDetails') {
      return <EditDetails navigator={navigator} {...route.passProps}  />
    }
  },

  render() {
    return (
        <Navigator
    style={{ flex:1 }}
    initialRoute={{ name: 'AwesomeProject' }}
    renderScene={ this.renderScene } />
  )
  }
});


var SCREEN_WIDTH = require('Dimensions').get('window').width;

var BaseConfig = Navigator.SceneConfigs.FloatFromRight;

var CustomLeftToRightGesture = Object.assign({}, BaseConfig.gestures.pop, {
  snapVelocity: 8,
  edgeHitWidth: SCREEN_WIDTH,
});

var CustomSceneConfig = Object.assign({}, BaseConfig, {
  springTension: 100,
  springFriction: 1,
  gestures: {
    pop: CustomLeftToRightGesture,
  }
});

const styles = StyleSheet.create({

  header: {
    fontSize: 24,
    textAlign: 'center',
    marginBottom: 10,
    color: '#1565C0',
  },
  container: {
    flex: 1,
    justifyContent: 'center',
    alignItems: 'center',
    backgroundColor: 'white',
  },
  listView: {
    width: 320,
    paddingTop: 1,
    backgroundColor: 'transparent',
  },
  holder: {
    flex: 0.25,
    justifyContent: 'center',
  },
  text: {
    fontSize: 50,
    backgroundColor: 'red'
  },
  viewDetails: {
    margin: 9
  },
  instructions: {
    textAlign: 'center',
    marginBottom: 5,
  },

});


AppRegistry.registerComponent('ReactProj', () => App);