from __future__ import print_function
import argparse
import torch
import torch.nn as nn
import torch.nn.functional as F
import torch.optim as optim
from torchvision import datasets, transforms
from torch.optim.lr_scheduler import StepLR
import os
from tqdm import tqdm
import matplotlib.pyplot as plt


from utils.config_utils import read_args, load_config, Dict2Object


class Net(nn.Module):
    def __init__(self):
        super(Net, self).__init__()
        self.conv1 = nn.Conv2d(1, 32, 3, 1)
        self.conv2 = nn.Conv2d(32, 64, 3, 1)
        self.dropout1 = nn.Dropout(0.25)
        self.dropout2 = nn.Dropout(0.5)
        self.fc1 = nn.Linear(9216, 128)
        self.fc2 = nn.Linear(128, 10)

    def forward(self, x):
        x = self.conv1(x)
        x = F.relu(x)
        x = self.conv2(x)
        x = F.relu(x)
        x = F.max_pool2d(x, 2)
        x = self.dropout1(x)
        x = torch.flatten(x, 1)
        x = self.fc1(x)
        x = F.relu(x)
        x = self.dropout2(x)
        x = self.fc2(x)
        output = F.log_softmax(x, dim=1)
        return output


def train(args, model, device, train_loader, optimizer, epoch):
    """
    tain the model and return the training accuracy
    :param args: input arguments
    :param model: neural network model
    :param device: the device where model stored
    :param train_loader: data loader
    :param optimizer: optimizer
    :param epoch: current epoch
    :return:
    """
    correct = 0
    total_loss = 0
    model.train()
    for batch_idx, (data, target) in enumerate(train_loader):
        # print('Current batch:', batch_idx)
        data, target = data.to(device), target.to(device)
        optimizer.zero_grad()
        output = model(data)
        loss = F.nll_loss(output, target)
        loss.backward()
        optimizer.step()
        '''Fill your code'''
        # Compute training accuracy and loss
        with torch.no_grad():
            data, target = data.to(device), target.to(device)
            output = model(data)
            total_loss += F.nll_loss(output, target).item()
            pred = output.argmax(dim=1, keepdim=True)
            correct += pred.eq(target.view_as(pred)).sum().item()

    training_acc, training_loss = 100.*correct/len(train_loader.dataset), total_loss/len(train_loader.dataset)
    return training_acc, training_loss


def test(model, device, test_loader):
    """
    test the model and return the tesing accuracy
    :param model: neural network model
    :param device: the device where model stored
    :param test_loader: data loader
    :return:
    """
    model.eval()
    test_loss = 0
    correct = 0
    with torch.no_grad():
        for data, target in test_loader:
            '''Fill your code'''
            data, target = data.to(device), target.to(device)
            output = model(data)
            test_loss += F.nll_loss(output, target).item()
            pred = output.argmax(dim=1, keepdim=True)
            correct += pred.eq(target.view_as(pred)).sum().item()

            
    testing_acc, testing_loss = 100.*correct/len(test_loader.dataset), test_loss/len(test_loader.dataset)
    return testing_acc, testing_loss


def plot():
    """
    plot the model peformance
    :param epoches: recorded epoches
    :param performance: recorded performance
    :return:
    """
    """Fill your code"""
    def convert(ele):
        return float(ele)

    record_files = ['Seed_123', 'Seed_321', 'Seed_666']
    record_names = os.listdir(f'./Seed_123')
    for record_name in record_names:
        for record_file in record_files:
            with open(os.path.join(record_file, record_name)) as f:
                record = f.readlines()
            record = list(map(convert, record))
            plt.figure()
            plt.plot(record)
            plt.title(f'{record_file} {record_name[:-4]}')
            plt.xlabel('Epochs')
            plt.ylabel('Loss/Accuracy')
            # plt.legend()
            plt.tight_layout()
            plt.savefig(f'./imgs/{record_file} {record_name[:-4]}.png')
            # plt.show()

def run(config,dataset1, train_kwargs, dataset2, test_kwargs, device):

    """add random seed to the DataLoader, pls modify this function"""
    torch.manual_seed(config.seed)
    train_loader = torch.utils.data.DataLoader(dataset1, **train_kwargs)
    test_loader = torch.utils.data.DataLoader(dataset2, **test_kwargs)

    model = Net().to(device)
    optimizer = optim.Adadelta(model.parameters(), lr=config.lr)

    """record the performance"""
    epoches = []
    training_accuracies = []
    training_loss = []
    testing_accuracies = []
    testing_loss = []

    print('Start Training...')
    scheduler = StepLR(optimizer, step_size=1, gamma=config.gamma)
    for epoch in range(1, config.epochs + 1):
        train_acc, train_loss = train(config, model, device, train_loader, optimizer, epoch)
        """record training info, Fill your code"""
        training_loss.append(train_loss)
        training_accuracies.append(train_acc)

        test_acc, test_loss = test(model, device, test_loader)
        """record testing info, Fill your code"""
        testing_accuracies.append(test_acc)
        testing_loss.append(test_loss)

        scheduler.step(epoch)
        """update the records, Fill your code"""
        epoches.append(epoch)

        print(f'epoch:{epoch}\ntrain loss:{train_loss}\ntrain accuracy:{train_acc}\ntest loss:{test_loss}\ntest accuracy:{test_acc}\nseed:{config.seed}')
    else:
        if not os.path.exists(f'Seed_{config.seed}'):
            os.makedirs(f'Seed_{config.seed}')

        with open(os.path.join(f'Seed_{config.seed}', 'training_loss_seed.txt'), 'w') as f:
            f.write('\n'.join(list(map(str, training_loss))))
        with open(os.path.join(f'Seed_{config.seed}', 'training_accuracies.txt'), 'w') as f:
            f.write('\n'.join(list(map(str, training_accuracies))))
        with open(os.path.join(f'Seed_{config.seed}', 'testing_loss.txt'), 'w') as f:
            f.write('\n'.join(list(map(str, testing_loss))))
        with open(os.path.join(f'Seed_{config.seed}', 'testing_accuracies.txt'), 'w') as f:
            f.write('\n'.join(list(map(str, testing_accuracies))))
            
    if config.save_model:
        torch.save(model.state_dict(), "mnist_cnn.pt")

def plot_mean():
    """
    Read the recorded results.
    Plot the mean results after three runs.
    :return:
    """
    """fill your code"""
    def convert(ele):
        return float(ele)

    record_files = ['Seed_123', 'Seed_321', 'Seed_666']
    record_names = os.listdir(f'./Seed_123')
    for record_name in record_names:
        plt.figure()
        for record_file in record_files:
            with open(os.path.join(record_file, record_name)) as f:
                record = f.readlines()
            record = list(map(convert, record))
            aver = sum(record)/len(record)
            plt.bar(record_file, aver, label=record_file)
        # plt.xticks(range(3), record_files)
        plt.legend()
        plt.ylabel(record_name[:-4])
        plt.title('Model Average Performance')
        plt.tight_layout()
        plt.savefig('./imgs/Model Average Performance '+record_name[:-4]+'.png')
        # plt.show()
    

if __name__ == '__main__':
    arg = read_args()

    """toad training settings"""
    config = load_config(arg)
    # config = load_config()

    print('Setting device...')
    use_cuda = not config.no_cuda and torch.cuda.is_available()
    use_mps = not config.no_mps and torch.backends.mps.is_available()

    if use_cuda:
        device = torch.device("cuda")
    elif use_mps:
        device = torch.device("mps")
    else:
        device = torch.device("cpu")

    print('Setting batch_size...')
    train_kwargs = {'batch_size': config.batch_size, 'shuffle': True}
    test_kwargs = {'batch_size': config.test_batch_size, 'shuffle': True}

    # download data
    print('Loading data...')
    transform = transforms.Compose([
        transforms.ToTensor(),
        transforms.Normalize((0.1307,), (0.3081,))
    ])
    dataset1 = datasets.MNIST('./data', train=True, download=True, transform=transform)
    dataset2 = datasets.MNIST('./data', train=False, transform=transform)

    import torch.multiprocessing as mp # 这里不需要使用pytorch的multiprocessing
    mp.set_start_method('spawn')

    pool = []
    for seed in [123,321,666]:
        config.seed=seed
        # dataset1, train_kwargs, dataset2, test_kwargs, device,
        process = mp.Process(target=run, args=(config,dataset1, train_kwargs, dataset2, test_kwargs, device,)) 
        # 每个进程都执行
        process.start()
        pool.append(process)

    for p in pool:
        p.join()  # 等待所有进程执行完毕

    """plot the performance of model"""
    plot()

    """plot the mean results"""
    plot_mean()

