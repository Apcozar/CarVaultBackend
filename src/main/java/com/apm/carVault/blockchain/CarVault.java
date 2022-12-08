package com.apm.carVault.blockchain;

import io.reactivex.Flowable;
import io.reactivex.functions.Function;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Callable;
import org.web3j.abi.EventEncoder;
import org.web3j.abi.TypeReference;
import org.web3j.abi.datatypes.Address;
import org.web3j.abi.datatypes.Bool;
import org.web3j.abi.datatypes.DynamicArray;
import org.web3j.abi.datatypes.DynamicStruct;
import org.web3j.abi.datatypes.Event;
import org.web3j.abi.datatypes.Type;
import org.web3j.abi.datatypes.Utf8String;
import org.web3j.abi.datatypes.generated.Uint16;
import org.web3j.abi.datatypes.generated.Uint256;
import org.web3j.abi.datatypes.generated.Uint32;
import org.web3j.abi.datatypes.generated.Uint8;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.DefaultBlockParameter;
import org.web3j.protocol.core.RemoteCall;
import org.web3j.protocol.core.RemoteFunctionCall;
import org.web3j.protocol.core.methods.request.EthFilter;
import org.web3j.protocol.core.methods.response.BaseEventResponse;
import org.web3j.protocol.core.methods.response.Log;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.tx.Contract;
import org.web3j.tx.TransactionManager;
import org.web3j.tx.gas.ContractGasProvider;

/**
 * <p>Auto generated code.
 * <p><strong>Do not modify!</strong>
 * <p>Please use the <a href="https://docs.web3j.io/command_line.html">web3j command line tools</a>,
 * or the org.web3j.codegen.SolidityFunctionWrapperGenerator in the
 * <a href="https://github.com/web3j/web3j/tree/master/codegen">codegen module</a> to update.
 *
 * <p>Generated with web3j version 1.4.1.
 */
@SuppressWarnings("rawtypes")
public class CarVault extends Contract {
    public static final String BINARY = "608060405234801561001057600080fd5b50600080546001600160a01b03191633179055612439806100326000396000f3fe608060405234801561001057600080fd5b50600436106101215760003560e01c80638da5cb5b116100ad578063e7469bc211610071578063e7469bc2146102b2578063e774e659146102c5578063ea725b93146102d8578063f3766414146102eb578063fd76ce041461030b57600080fd5b80638da5cb5b1461022d5780638f32d59b146102485780639aeb325714610266578063d1b3b40514610279578063d8542b501461028c57600080fd5b80634eff12e5116100f45780634eff12e5146101a157806376145e2a146101b4578063804069c8146101c75780638565ef48146101ec57806388bf083b1461020d57600080fd5b806317c39286146101265780633d5a1f4b146101445780634859b433146101595780634a8c403814610181575b600080fd5b61012e61031e565b60405161013b9190611dc5565b60405180910390f35b610157610152366004611e87565b6104dc565b005b61016c610167366004611edb565b6105f0565b60405163ffffffff909116815260200161013b565b61019461018f366004611edb565b61064e565b60405161013b9190611f1d565b6101576101af366004611f93565b610795565b6101576101c2366004611fff565b610941565b6101da6101d5366004611edb565b610a51565b60405160ff909116815260200161013b565b6101ff6101fa366004611edb565b610ab4565b60405190815260200161013b565b61022061021b366004611edb565b610b08565b60405161013b919061204b565b6000546040516001600160a01b03909116815260200161013b565b6000546001600160a01b03163314604051901515815260200161013b565b610157610274366004612079565b610be7565b610157610287366004612109565b610f29565b61029f61029a366004611edb565b611032565b60405161ffff909116815260200161013b565b6101576102c0366004612154565b611096565b61012e6102d3366004611edb565b6111ec565b6101576102e63660046121a0565b611505565b6102fe6102f9366004611edb565b61164d565b60405161013b9190612205565b610157610319366004611edb565b61182a565b60606001805480602002602001604051908101604052809291908181526020016000905b828210156104d357838290600052602060002090600302016040518060a001604052908160008201805461037590612218565b80601f01602080910402602001604051908101604052809291908181526020018280546103a190612218565b80156103ee5780601f106103c3576101008083540402835291602001916103ee565b820191906000526020600020905b8154815290600101906020018083116103d157829003601f168201915b5050505050815260200160018201805461040790612218565b80601f016020809104026020016040519081016040528092919081815260200182805461043390612218565b80156104805780601f1061045557610100808354040283529160200191610480565b820191906000526020600020905b81548152906001019060200180831161046357829003601f168201915b50505091835250506002919091015463ffffffff8116602080840191909152600160201b820461ffff166040840152600160301b90910460ff166060909201919091529082526001929092019101610342565b50505050905090565b6000546001600160a01b0316331461050f5760405162461bcd60e51b815260040161050690612252565b60405180910390fd5b60005b6001548110156105ea57838360405160200161052f92919061229a565b6040516020818303038152906040528051906020012060018281548110610558576105586122aa565b906000526020600020906003020160010160405160200161057991906122c0565b60405160208183030381529060405280519060200120036105d85781600182815481106105a8576105a86122aa565b906000526020600020906003020160020160046101000a81548161ffff021916908361ffff1602179055506105ea565b806105e281612371565b915050610512565b50505050565b600080546001600160a01b0316331461061b5760405162461bcd60e51b815260040161050690612252565b6002838360405161062d92919061229a565b9081526040519081900360200190206002015463ffffffff16905092915050565b6000546060906001600160a01b0316331461067b5760405162461bcd60e51b815260040161050690612252565b6003838360405161068d92919061229a565b9081526020016040518091039020805480602002602001604051908101604052809291908181526020016000905b8282101561078957838290600052602060002090600202016040518060400160405290816000820180546106ee90612218565b80601f016020809104026020016040519081016040528092919081815260200182805461071a90612218565b80156107675780601f1061073c57610100808354040283529160200191610767565b820191906000526020600020905b81548152906001019060200180831161074a57829003601f168201915b50505050508152602001600182015481525050815260200190600101906106bb565b50505050905092915050565b6000546001600160a01b031633146107bf5760405162461bcd60e51b815260040161050690612252565b60005b6001548110156108e25782826040516020016107df92919061229a565b6040516020818303038152906040528051906020012060018281548110610808576108086122aa565b906000526020600020906003020160010160405160200161082991906122c0565b60405160208183030381529060405280519060200120036108d057848460018381548110610859576108596122aa565b600091825260209091206108739360039092020191611b08565b5060018181548110610887576108876122aa565b600091825260209091206003909102016002018054600160301b900460ff169060066108b28361238a565b91906101000a81548160ff021916908360ff160217905550506108e2565b806108da81612371565b9150506107c2565b5081816040516108f392919061229a565b6040518091039020848460405161090b92919061229a565b604051908190038120907f0bdba9547940f014375f5c2ba0ba7229f1967ccb73b21ebc515b682265b91d9b90600090a350505050565b6000546001600160a01b0316331461096b5760405162461bcd60e51b815260040161050690612252565b6003838360405161097d92919061229a565b9081526020016040518091039020604051806040016040528085858080601f016020809104026020016040519081016040528093929190818152602001838380828437600092018290525093855250505060209182018590528354600181018555938152819020825180519394600202909101926109fe9284920190611b8c565b50602082015181600101555050808383604051610a1c92919061229a565b604051908190038120907f6515d13c8655300178683ca1144bbd48ff974b5f2644afd72e4914b5f64f968690600090a3505050565b600080546001600160a01b03163314610a7c5760405162461bcd60e51b815260040161050690612252565b60028383604051610a8e92919061229a565b9081526040519081900360200190206002015460ff600160301b90910416905092915050565b600080546001600160a01b03163314610adf5760405162461bcd60e51b815260040161050690612252565b60048383604051610af192919061229a565b908152602001604051809103902054905092915050565b6000546060906001600160a01b03163314610b355760405162461bcd60e51b815260040161050690612252565b60028383604051610b4792919061229a565b9081526040519081900360200190208054610b6190612218565b80601f0160208091040260200160405190810160405280929190818152602001828054610b8d90612218565b8015610bda5780601f10610baf57610100808354040283529160200191610bda565b820191906000526020600020905b815481529060010190602001808311610bbd57829003601f168201915b5050505050905092915050565b6000546001600160a01b03163314610c115760405162461bcd60e51b815260040161050690612252565b6040518060a0016040528087878080601f016020809104026020016040519081016040528093929190818152602001838380828437600092019190915250505090825250604080516020601f88018190048102820181019092528681529181019190879087908190840183828082843760009201919091525050509082525063ffffffff8416602082015261ffff8316604080830191909152600160609092019190915251600290610cc6908790879061229a565b90815260200160405180910390206000820151816000019080519060200190610cf0929190611b8c565b506020828101518051610d099260018501920190611b8c565b5060408281015160029092018054606085015160809095015160ff16600160301b0260ff60301b1961ffff909616600160201b0265ffffffffffff1990921663ffffffff90951694909417179390931691909117909155805160c06020601f8901819004028201810190925260a081018781526001928291908a908a9081908501838280828437600092019190915250505090825250604080516020601f890181900481028201810190925287815291810191908890889081908401838280828437600092018290525093855250505063ffffffff861660208084019190915261ffff861660408401526001606090930183905284549283018555938152839020825180519394600390930290910192610e269284920190611b8c565b506020828101518051610e3f9260018501920190611b8c565b5060408281015160029092018054606085015160809095015160ff16600160301b0260ff60301b1961ffff909616600160201b0265ffffffffffff1990921663ffffffff9095169490941717939093169190911790915551600490610ea7908890889061229a565b9081526040519081900360200190208054906000610ec483612371565b91905055508383604051610ed992919061229a565b60405180910390208686604051610ef192919061229a565b604051908190038120907f22479197ee034f2b15cd3df9d4d88e1c74323f9f456148c73724cd7814ba998890600090a3505050505050565b6000546001600160a01b03163314610f535760405162461bcd60e51b815260040161050690612252565b60005b6001548110156105ea578383604051602001610f7392919061229a565b6040516020818303038152906040528051906020012060018281548110610f9c57610f9c6122aa565b9060005260206000209060030201600101604051602001610fbd91906122c0565b6040516020818303038152906040528051906020012003611020578160018281548110610fec57610fec6122aa565b906000526020600020906003020160020160006101000a81548163ffffffff021916908363ffffffff1602179055506105ea565b8061102a81612371565b915050610f56565b600080546001600160a01b0316331461105d5760405162461bcd60e51b815260040161050690612252565b6002838360405161106f92919061229a565b9081526040519081900360200190206002015461ffff600160201b90910416905092915050565b6000546001600160a01b031633146110c05760405162461bcd60e51b815260040161050690612252565b6000600383836040516110d492919061229a565b9081526020016040518091039020905060005b81548110156111e55784828281548110611103576111036122aa565b906000526020600020906002020160010154036111d3578154829061112a906001906123a9565b8154811061113a5761113a6122aa565b906000526020600020906002020182828154811061115a5761115a6122aa565b9060005260206000209060020201600082018160000190805461117c90612218565b611187929190611c00565b5060019182015491015581548290806111a2576111a26123c0565b600082815260208120600019909201916002830201906111c28282611c7b565b6001820160009055505090556111e5565b806111dd81612371565b9150506110e7565b5050505050565b6000546060906001600160a01b031633146112195760405162461bcd60e51b815260040161050690612252565b60006004848460405161122d92919061229a565b90815260200160405180910390205467ffffffffffffffff811115611254576112546123d6565b6040519080825280602002602001820160405280156112b057816020015b6040805160a0810182526060808252602082018190526000928201839052810182905260808101919091528152602001906001900390816112725790505b5090506000805b6001548110156114fb5785856040516020016112d492919061229a565b60405160208183030381529060405280519060200120600182815481106112fd576112fd6122aa565b906000526020600020906003020160000160405160200161131e91906122c0565b60405160208183030381529060405280519060200120036114e9576001818154811061134c5761134c6122aa565b90600052602060002090600302016040518060a001604052908160008201805461137590612218565b80601f01602080910402602001604051908101604052809291908181526020018280546113a190612218565b80156113ee5780601f106113c3576101008083540402835291602001916113ee565b820191906000526020600020905b8154815290600101906020018083116113d157829003601f168201915b5050505050815260200160018201805461140790612218565b80601f016020809104026020016040519081016040528092919081815260200182805461143390612218565b80156114805780601f1061145557610100808354040283529160200191611480565b820191906000526020600020905b81548152906001019060200180831161146357829003601f168201915b50505091835250506002919091015463ffffffff81166020830152600160201b810461ffff166040830152600160301b900460ff1660609091015283518490849081106114cf576114cf6122aa565b602002602001018190525081806114e590612371565b9250505b806114f381612371565b9150506112b7565b5090949350505050565b6000546001600160a01b0316331461152f5760405162461bcd60e51b815260040161050690612252565b60005b6001548110156111e557848460405160200161154f92919061229a565b6040516020818303038152906040528051906020012060018281548110611578576115786122aa565b906000526020600020906003020160010160405160200161159991906122c0565b604051602081830303815290604052805190602001200361163b5782600182815481106115c8576115c86122aa565b906000526020600020906003020160020160006101000a81548163ffffffff021916908363ffffffff160217905550816001828154811061160b5761160b6122aa565b906000526020600020906003020160020160046101000a81548161ffff021916908361ffff1602179055506111e5565b8061164581612371565b915050611532565b6040805160a0810182526060808252602082018190526000928201839052810182905260808101919091526000546001600160a01b031633146116a25760405162461bcd60e51b815260040161050690612252565b600283836040516116b492919061229a565b90815260200160405180910390206040518060a00160405290816000820180546116dd90612218565b80601f016020809104026020016040519081016040528092919081815260200182805461170990612218565b80156117565780601f1061172b57610100808354040283529160200191611756565b820191906000526020600020905b81548152906001019060200180831161173957829003601f168201915b5050505050815260200160018201805461176f90612218565b80601f016020809104026020016040519081016040528092919081815260200182805461179b90612218565b80156117e85780601f106117bd576101008083540402835291602001916117e8565b820191906000526020600020905b8154815290600101906020018083116117cb57829003601f168201915b50505091835250506002919091015463ffffffff81166020830152600160201b810461ffff166040830152600160301b900460ff166060909101529392505050565b6000546001600160a01b031633146118545760405162461bcd60e51b815260040161050690612252565b60005b600154811015611b0357828260405160200161187492919061229a565b604051602081830303815290604052805190602001206001828154811061189d5761189d6122aa565b90600052602060002090600302016001016040516020016118be91906122c0565b6040516020818303038152906040528051906020012003611af157600180546118e89082906123a9565b815481106118f8576118f86122aa565b906000526020600020906003020160018281548110611919576119196122aa565b9060005260206000209060030201600082018160000190805461193b90612218565b611946929190611c00565b50600182018160010190805461195b90612218565b611966929190611c00565b506002918201805491909201805463ffffffff19811663ffffffff9093169283178255835461ffff600160201b91829004160265ffffffffffff1990911690921791909117808255915460ff600160301b91829004160260ff60301b199092169190911790556001805460049190839081106119e4576119e46122aa565b9060005260206000209060030201600001604051611a0291906122c0565b9081526040519081900360200190208054906000611a1f836123ec565b91905055506001805480611a3557611a356123c0565b60008281526020812060001990920191600383020190611a558282611c7b565b611a63600183016000611c7b565b506002908101805466ffffffffffffff191690559155604051611a89908590859061229a565b9081526040519081900360200190206000611aa48282611c7b565b611ab2600183016000611c7b565b50600201805466ffffffffffffff19169055604051600390611ad7908590859061229a565b90815260200160405180910390206000611af19190611cb8565b80611afb81612371565b915050611857565b505050565b828054611b1490612218565b90600052602060002090601f016020900481019282611b365760008555611b7c565b82601f10611b4f5782800160ff19823516178555611b7c565b82800160010185558215611b7c579182015b82811115611b7c578235825591602001919060010190611b61565b50611b88929150611cd9565b5090565b828054611b9890612218565b90600052602060002090601f016020900481019282611bba5760008555611b7c565b82601f10611bd357805160ff1916838001178555611b7c565b82800160010185558215611b7c579182015b82811115611b7c578251825591602001919060010190611be5565b828054611c0c90612218565b90600052602060002090601f016020900481019282611c2e5760008555611b7c565b82601f10611c3f5780548555611b7c565b82800160010185558215611b7c57600052602060002091601f016020900482015b82811115611b7c578254825591600101919060010190611c60565b508054611c8790612218565b6000825580601f10611c97575050565b601f016020900490600052602060002090810190611cb59190611cd9565b50565b5080546000825560020290600052602060002090810190611cb59190611cee565b5b80821115611b885760008155600101611cda565b80821115611b88576000611d028282611c7b565b5060006001820155600201611cee565b6000815180845260005b81811015611d3857602081850181015186830182015201611d1c565b81811115611d4a576000602083870101525b50601f01601f19169290920160200192915050565b6000815160a08452611d7460a0850182611d12565b905060208301518482036020860152611d8d8282611d12565b91505063ffffffff604084015116604085015261ffff606084015116606085015260ff60808401511660808501528091505092915050565b6000602080830181845280855180835260408601915060408160051b870101925083870160005b82811015611e1a57603f19888603018452611e08858351611d5f565b94509285019290850190600101611dec565b5092979650505050505050565b60008083601f840112611e3957600080fd5b50813567ffffffffffffffff811115611e5157600080fd5b602083019150836020828501011115611e6957600080fd5b9250929050565b803561ffff81168114611e8257600080fd5b919050565b600080600060408486031215611e9c57600080fd5b833567ffffffffffffffff811115611eb357600080fd5b611ebf86828701611e27565b9094509250611ed2905060208501611e70565b90509250925092565b60008060208385031215611eee57600080fd5b823567ffffffffffffffff811115611f0557600080fd5b611f1185828601611e27565b90969095509350505050565b60006020808301818452808551808352604092508286019150828160051b87010184880160005b83811015611f8557888303603f1901855281518051878552611f6888860182611d12565b918901519489019490945294870194925090860190600101611f44565b509098975050505050505050565b60008060008060408587031215611fa957600080fd5b843567ffffffffffffffff80821115611fc157600080fd5b611fcd88838901611e27565b90965094506020870135915080821115611fe657600080fd5b50611ff387828801611e27565b95989497509550505050565b60008060006040848603121561201457600080fd5b833567ffffffffffffffff81111561202b57600080fd5b61203786828701611e27565b909790965060209590950135949350505050565b60208152600061205e6020830184611d12565b9392505050565b803563ffffffff81168114611e8257600080fd5b6000806000806000806080878903121561209257600080fd5b863567ffffffffffffffff808211156120aa57600080fd5b6120b68a838b01611e27565b909850965060208901359150808211156120cf57600080fd5b506120dc89828a01611e27565b90955093506120ef905060408801612065565b91506120fd60608801611e70565b90509295509295509295565b60008060006040848603121561211e57600080fd5b833567ffffffffffffffff81111561213557600080fd5b61214186828701611e27565b9094509250611ed2905060208501612065565b60008060006040848603121561216957600080fd5b83359250602084013567ffffffffffffffff81111561218757600080fd5b61219386828701611e27565b9497909650939450505050565b600080600080606085870312156121b657600080fd5b843567ffffffffffffffff8111156121cd57600080fd5b6121d987828801611e27565b90955093506121ec905060208601612065565b91506121fa60408601611e70565b905092959194509250565b60208152600061205e6020830184611d5f565b600181811c9082168061222c57607f821691505b60208210810361224c57634e487b7160e01b600052602260045260246000fd5b50919050565b60208082526028908201527f46756e6374696f6e2061636365737369626c65206f6e6c7920627920746865206040820152676f776e657220212160c01b606082015260800190565b8183823760009101908152919050565b634e487b7160e01b600052603260045260246000fd5b600080835481600182811c9150808316806122dc57607f831692505b602080841082036122fb57634e487b7160e01b86526022600452602486fd5b81801561230f57600181146123205761234d565b60ff1986168952848901965061234d565b60008a81526020902060005b868110156123455781548b82015290850190830161232c565b505084890196505b509498975050505050505050565b634e487b7160e01b600052601160045260246000fd5b6000600182016123835761238361235b565b5060010190565b600060ff821660ff81036123a0576123a061235b565b60010192915050565b6000828210156123bb576123bb61235b565b500390565b634e487b7160e01b600052603160045260246000fd5b634e487b7160e01b600052604160045260246000fd5b6000816123fb576123fb61235b565b50600019019056fea2646970667358221220cda6709fc34bb34349c4aa2ff7d21445a6a01af118076a7c55ef20583c22600964736f6c634300080e0033";

    public static final String FUNC_ADDDOCUMENT = "addDocument";

    public static final String FUNC_CHANGEOWNERSHIP = "changeOwnerShip";

    public static final String FUNC_CREATECAR = "createCar";

    public static final String FUNC_DELETECAR = "deleteCar";

    public static final String FUNC_DELETEDOCUMENT = "deleteDocument";

    public static final String FUNC_GETCARBYVIN = "getCarByVin";

    public static final String FUNC_GETCARS = "getCars";

    public static final String FUNC_GETCARSBYOWNER = "getCarsByOwner";

    public static final String FUNC_GETCOUNTCARSBYOWNER = "getCountCarsByOwner";

    public static final String FUNC_GETCVBYVIN = "getCvByVin";

    public static final String FUNC_GETDOCUMENTSBYVIN = "getDocumentsByVin";

    public static final String FUNC_GETKMSBYVIN = "getKmsByVin";

    public static final String FUNC_GETNUMOWNERSBYVIN = "getNumOwnersByVin";

    public static final String FUNC_GETOWNERBYVIN = "getOwnerByVin";

    public static final String FUNC_ISOWNER = "isOwner";

    public static final String FUNC_OWNER = "owner";

    public static final String FUNC_UPDATECV = "updateCv";

    public static final String FUNC_UPDATEKMS = "updateKms";

    public static final String FUNC_UPDATEKMSANDCV = "updateKmsAndCv";

    public static final Event CHANGEOWNER_EVENT = new Event("ChangeOwner",
            Arrays.<TypeReference<?>>asList(new TypeReference<Utf8String>(true) {}, new TypeReference<Utf8String>(true) {}));
    ;

    public static final Event NEWCAR_EVENT = new Event("NewCar",
            Arrays.<TypeReference<?>>asList(new TypeReference<Utf8String>(true) {}, new TypeReference<Utf8String>(true) {}));
    ;

    public static final Event NEWDOCUMENT_EVENT = new Event("NewDocument",
            Arrays.<TypeReference<?>>asList(new TypeReference<Utf8String>(true) {}, new TypeReference<Uint256>(true) {}));
    ;

    @Deprecated
    protected CarVault(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    protected CarVault(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, credentials, contractGasProvider);
    }

    @Deprecated
    protected CarVault(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    protected CarVault(String contractAddress, Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public List<ChangeOwnerEventResponse> getChangeOwnerEvents(TransactionReceipt transactionReceipt) {
        List<Contract.EventValuesWithLog> valueList = extractEventParametersWithLog(CHANGEOWNER_EVENT, transactionReceipt);
        ArrayList<ChangeOwnerEventResponse> responses = new ArrayList<ChangeOwnerEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            ChangeOwnerEventResponse typedResponse = new ChangeOwnerEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.newOwner = (byte[]) eventValues.getIndexedValues().get(0).getValue();
            typedResponse.vin = (byte[]) eventValues.getIndexedValues().get(1).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public Flowable<ChangeOwnerEventResponse> changeOwnerEventFlowable(EthFilter filter) {
        return web3j.ethLogFlowable(filter).map(new Function<Log, ChangeOwnerEventResponse>() {
            @Override
            public ChangeOwnerEventResponse apply(Log log) {
                Contract.EventValuesWithLog eventValues = extractEventParametersWithLog(CHANGEOWNER_EVENT, log);
                ChangeOwnerEventResponse typedResponse = new ChangeOwnerEventResponse();
                typedResponse.log = log;
                typedResponse.newOwner = (byte[]) eventValues.getIndexedValues().get(0).getValue();
                typedResponse.vin = (byte[]) eventValues.getIndexedValues().get(1).getValue();
                return typedResponse;
            }
        });
    }

    public Flowable<ChangeOwnerEventResponse> changeOwnerEventFlowable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(CHANGEOWNER_EVENT));
        return changeOwnerEventFlowable(filter);
    }

    public List<NewCarEventResponse> getNewCarEvents(TransactionReceipt transactionReceipt) {
        List<Contract.EventValuesWithLog> valueList = extractEventParametersWithLog(NEWCAR_EVENT, transactionReceipt);
        ArrayList<NewCarEventResponse> responses = new ArrayList<NewCarEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            NewCarEventResponse typedResponse = new NewCarEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.owner = (byte[]) eventValues.getIndexedValues().get(0).getValue();
            typedResponse.vin = (byte[]) eventValues.getIndexedValues().get(1).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public Flowable<NewCarEventResponse> newCarEventFlowable(EthFilter filter) {
        return web3j.ethLogFlowable(filter).map(new Function<Log, NewCarEventResponse>() {
            @Override
            public NewCarEventResponse apply(Log log) {
                Contract.EventValuesWithLog eventValues = extractEventParametersWithLog(NEWCAR_EVENT, log);
                NewCarEventResponse typedResponse = new NewCarEventResponse();
                typedResponse.log = log;
                typedResponse.owner = (byte[]) eventValues.getIndexedValues().get(0).getValue();
                typedResponse.vin = (byte[]) eventValues.getIndexedValues().get(1).getValue();
                return typedResponse;
            }
        });
    }

    public Flowable<NewCarEventResponse> newCarEventFlowable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(NEWCAR_EVENT));
        return newCarEventFlowable(filter);
    }

    public List<NewDocumentEventResponse> getNewDocumentEvents(TransactionReceipt transactionReceipt) {
        List<Contract.EventValuesWithLog> valueList = extractEventParametersWithLog(NEWDOCUMENT_EVENT, transactionReceipt);
        ArrayList<NewDocumentEventResponse> responses = new ArrayList<NewDocumentEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            NewDocumentEventResponse typedResponse = new NewDocumentEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.vin = (byte[]) eventValues.getIndexedValues().get(0).getValue();
            typedResponse.docId = (BigInteger) eventValues.getIndexedValues().get(1).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public Flowable<NewDocumentEventResponse> newDocumentEventFlowable(EthFilter filter) {
        return web3j.ethLogFlowable(filter).map(new Function<Log, NewDocumentEventResponse>() {
            @Override
            public NewDocumentEventResponse apply(Log log) {
                Contract.EventValuesWithLog eventValues = extractEventParametersWithLog(NEWDOCUMENT_EVENT, log);
                NewDocumentEventResponse typedResponse = new NewDocumentEventResponse();
                typedResponse.log = log;
                typedResponse.vin = (byte[]) eventValues.getIndexedValues().get(0).getValue();
                typedResponse.docId = (BigInteger) eventValues.getIndexedValues().get(1).getValue();
                return typedResponse;
            }
        });
    }

    public Flowable<NewDocumentEventResponse> newDocumentEventFlowable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(NEWDOCUMENT_EVENT));
        return newDocumentEventFlowable(filter);
    }

    public RemoteFunctionCall<TransactionReceipt> addDocument(String _vin, BigInteger _docId) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                FUNC_ADDDOCUMENT,
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Utf8String(_vin),
                        new org.web3j.abi.datatypes.generated.Uint256(_docId)),
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<TransactionReceipt> changeOwnerShip(String _newOwner, String _vin) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                FUNC_CHANGEOWNERSHIP,
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Utf8String(_newOwner),
                        new org.web3j.abi.datatypes.Utf8String(_vin)),
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<TransactionReceipt> createCar(String _owner, String _vin, BigInteger _kms, BigInteger _cv) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                FUNC_CREATECAR,
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Utf8String(_owner),
                        new org.web3j.abi.datatypes.Utf8String(_vin),
                        new org.web3j.abi.datatypes.generated.Uint32(_kms),
                        new org.web3j.abi.datatypes.generated.Uint16(_cv)),
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<TransactionReceipt> deleteCar(String _vin) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                FUNC_DELETECAR,
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Utf8String(_vin)),
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<TransactionReceipt> deleteDocument(BigInteger _docId, String _vin) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                FUNC_DELETEDOCUMENT,
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(_docId),
                        new org.web3j.abi.datatypes.Utf8String(_vin)),
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<Car> getCarByVin(String _vin) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_GETCARBYVIN,
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Utf8String(_vin)),
                Arrays.<TypeReference<?>>asList(new TypeReference<Car>() {}));
        return executeRemoteCallSingleValueReturn(function, Car.class);
    }

    public RemoteFunctionCall<List> getCars() {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_GETCARS,
                Arrays.<Type>asList(),
                Arrays.<TypeReference<?>>asList(new TypeReference<DynamicArray<Car>>() {}));
        return new RemoteFunctionCall<List>(function,
                new Callable<List>() {
                    @Override
                    @SuppressWarnings("unchecked")
                    public List call() throws Exception {
                        List<Type> result = (List<Type>) executeCallSingleValueReturn(function, List.class);
                        return convertToNative(result);
                    }
                });
    }

    public RemoteFunctionCall<List> getCarsByOwner(String _owner) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_GETCARSBYOWNER,
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Utf8String(_owner)),
                Arrays.<TypeReference<?>>asList(new TypeReference<DynamicArray<Car>>() {}));
        return new RemoteFunctionCall<List>(function,
                new Callable<List>() {
                    @Override
                    @SuppressWarnings("unchecked")
                    public List call() throws Exception {
                        List<Type> result = (List<Type>) executeCallSingleValueReturn(function, List.class);
                        return convertToNative(result);
                    }
                });
    }

    public RemoteFunctionCall<BigInteger> getCountCarsByOwner(String _owner) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_GETCOUNTCARSBYOWNER,
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Utf8String(_owner)),
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteFunctionCall<BigInteger> getCvByVin(String _vin) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_GETCVBYVIN,
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Utf8String(_vin)),
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint16>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteFunctionCall<List> getDocumentsByVin(String _vin) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_GETDOCUMENTSBYVIN,
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Utf8String(_vin)),
                Arrays.<TypeReference<?>>asList(new TypeReference<DynamicArray<CarDocument>>() {}));
        return new RemoteFunctionCall<List>(function,
                new Callable<List>() {
                    @Override
                    @SuppressWarnings("unchecked")
                    public List call() throws Exception {
                        List<Type> result = (List<Type>) executeCallSingleValueReturn(function, List.class);
                        return convertToNative(result);
                    }
                });
    }

    public RemoteFunctionCall<BigInteger> getKmsByVin(String _vin) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_GETKMSBYVIN,
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Utf8String(_vin)),
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint32>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteFunctionCall<BigInteger> getNumOwnersByVin(String _vin) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_GETNUMOWNERSBYVIN,
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Utf8String(_vin)),
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint8>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteFunctionCall<String> getOwnerByVin(String _vin) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_GETOWNERBYVIN,
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Utf8String(_vin)),
                Arrays.<TypeReference<?>>asList(new TypeReference<Utf8String>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public RemoteFunctionCall<Boolean> isOwner() {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_ISOWNER,
                Arrays.<Type>asList(),
                Arrays.<TypeReference<?>>asList(new TypeReference<Bool>() {}));
        return executeRemoteCallSingleValueReturn(function, Boolean.class);
    }

    public RemoteFunctionCall<String> owner() {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_OWNER,
                Arrays.<Type>asList(),
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public RemoteFunctionCall<TransactionReceipt> updateCv(String _vin, BigInteger _cv) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                FUNC_UPDATECV,
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Utf8String(_vin),
                        new org.web3j.abi.datatypes.generated.Uint16(_cv)),
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<TransactionReceipt> updateKms(String _vin, BigInteger _kms) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                FUNC_UPDATEKMS,
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Utf8String(_vin),
                        new org.web3j.abi.datatypes.generated.Uint32(_kms)),
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<TransactionReceipt> updateKmsAndCv(String _vin, BigInteger _kms, BigInteger _cv) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                FUNC_UPDATEKMSANDCV,
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Utf8String(_vin),
                        new org.web3j.abi.datatypes.generated.Uint32(_kms),
                        new org.web3j.abi.datatypes.generated.Uint16(_cv)),
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    @Deprecated
    public static CarVault load(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return new CarVault(contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    @Deprecated
    public static CarVault load(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return new CarVault(contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    public static CarVault load(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        return new CarVault(contractAddress, web3j, credentials, contractGasProvider);
    }

    public static CarVault load(String contractAddress, Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        return new CarVault(contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public static RemoteCall<CarVault> deploy(Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        return deployRemoteCall(CarVault.class, web3j, credentials, contractGasProvider, BINARY, "");
    }

    @Deprecated
    public static RemoteCall<CarVault> deploy(Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return deployRemoteCall(CarVault.class, web3j, credentials, gasPrice, gasLimit, BINARY, "");
    }

    public static RemoteCall<CarVault> deploy(Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        return deployRemoteCall(CarVault.class, web3j, transactionManager, contractGasProvider, BINARY, "");
    }

    @Deprecated
    public static RemoteCall<CarVault> deploy(Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return deployRemoteCall(CarVault.class, web3j, transactionManager, gasPrice, gasLimit, BINARY, "");
    }

    public static class Car extends DynamicStruct {
        public String owner;

        public String vin;

        public BigInteger kms;

        public BigInteger cv;

        public BigInteger numOwners;

        public Car(String owner, String vin, BigInteger kms, BigInteger cv, BigInteger numOwners) {
            super(new org.web3j.abi.datatypes.Utf8String(owner),new org.web3j.abi.datatypes.Utf8String(vin),new org.web3j.abi.datatypes.generated.Uint32(kms),new org.web3j.abi.datatypes.generated.Uint16(cv),new org.web3j.abi.datatypes.generated.Uint8(numOwners));
            this.owner = owner;
            this.vin = vin;
            this.kms = kms;
            this.cv = cv;
            this.numOwners = numOwners;
        }

        public Car(Utf8String owner, Utf8String vin, Uint32 kms, Uint16 cv, Uint8 numOwners) {
            super(owner,vin,kms,cv,numOwners);
            this.owner = owner.getValue();
            this.vin = vin.getValue();
            this.kms = kms.getValue();
            this.cv = cv.getValue();
            this.numOwners = numOwners.getValue();
        }
    }

    public static class CarDocument extends DynamicStruct {
        public String vin;

        public BigInteger docId;

        public CarDocument(String vin, BigInteger docId) {
            super(new org.web3j.abi.datatypes.Utf8String(vin),new org.web3j.abi.datatypes.generated.Uint256(docId));
            this.vin = vin;
            this.docId = docId;
        }

        public CarDocument(Utf8String vin, Uint256 docId) {
            super(vin,docId);
            this.vin = vin.getValue();
            this.docId = docId.getValue();
        }
    }

    public static class ChangeOwnerEventResponse extends BaseEventResponse {
        public byte[] newOwner;

        public byte[] vin;
    }

    public static class NewCarEventResponse extends BaseEventResponse {
        public byte[] owner;

        public byte[] vin;
    }

    public static class NewDocumentEventResponse extends BaseEventResponse {
        public byte[] vin;

        public BigInteger docId;
    }
}
